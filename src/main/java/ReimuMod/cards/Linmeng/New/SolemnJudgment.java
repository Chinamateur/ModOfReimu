package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;


public class SolemnJudgment extends CustomCard {
    public static final String ID = "SolemnJudgment";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 0;

    public SolemnJudgment() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.ENEMY
        );
        this.isEthereal = true ;
        this.exhaust = true ;
    }
    public void onChoseThisOption() {
        AbstractDungeon.player.hand.addToHand(this);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p,p,p.currentHealth/2));
        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(m, new InflameEffect(m), 0.25F));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(m, new InflameEffect(m), 0.25F));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(m, new InflameEffect(m), 0.25F));
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.GOLD.cpy())));
            this.addToBot(new WaitAction(0.8F));
            if (!m.hasPower("Invincible")) {
                this.addToBot(new VFXAction(new GiantTextEffect(m.hb.cX, m.hb.cY)));
                this.addToTop(new InstantKillAction(m));
            } else {
                AbstractDungeon.actionManager.addToBottom(new LoseHPAction(m, (AbstractCreature) null, m.maxHealth));
            }
        }
    }
            //m.damage(new DamageInfo(p, m.currentHealth, DamageInfo.DamageType.HP_LOSS));
            //new WaitAction(0.8F);


    public void upgrade() {
        if (!this.upgraded) {
            this.isEthereal = false ;
            this.upgradeName();
            initializeDescription();
        }
    }
}
