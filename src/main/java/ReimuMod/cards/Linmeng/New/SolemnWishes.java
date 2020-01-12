package ReimuMod.cards.Linmeng.New;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class SolemnWishes extends CustomCard {
    public static final String ID = "SolemnWishes";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 0;

    public SolemnWishes() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.isEthereal = true ;
        this.exhaust = true ;
    }
    public void onChoseThisOption() {
        AbstractDungeon.player.hand.addToHand(this);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,p.maxHealth/2));
    }

    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            //this.upgradeDamage(2);
            //this.upgradeMagicNumber(1);
            this.isEthereal = false ;
            this.upgradeName();
            initializeDescription();
        }
    }
}
