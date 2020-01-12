package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

public class Crusade extends CustomCard {
    public static final String ID = "Crusade";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 2;
    private static final int D = 13;
    public Crusade() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY
        );
        this.baseDamage = D;
        this.baseMagicNumber= this.magicNumber = 3;
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = this.damage;
        m.createIntent();
        if (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_DEFEND || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_BUFF) {
            x *= 2 ;
            if (Settings.FAST_MODE) {
                this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY,setKami.Kami10.KamiColoer(2).cpy())));
            } else {
                this.addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY,setKami.Kami10.KamiColoer(2).cpy()), 0.4F));
            }

        }

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(
                        m,
                        new DamageInfo(p, x , this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HEAVY
                )
        );
        new setKami(this.magicNumber,"lei");
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
            //this.isEthereal = false;
            this.upgradeDamage(4);
            //this.upgradeBaseCost(4);
            //this.isInnate =true;
            //this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
