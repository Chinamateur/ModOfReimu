package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.X.TakeOffAction;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TakeOff extends CustomCard {
    public static final String ID = "TakeOff";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = -1;

    public TakeOff() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
    }
    public AbstractCard makeCopy() { return new TakeOff(); }
    public void use(AbstractPlayer p, AbstractMonster m) {
         this.addToBot(new TakeOffAction(this,this.energyOnUse));
    }


    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            this.exhaust = false ;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION ;
            this.upgradeName();
            initializeDescription();
        }
    }
}
