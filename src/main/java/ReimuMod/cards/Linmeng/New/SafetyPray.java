package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import ReimuMod.cards.Sign;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SafetyPray extends CustomCard {

    public static final String ID = "SafetyPray";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    public SafetyPray() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.COMMON,
                AbstractCard.CardTarget.SELF
        );
        /*
        if ( Settings.seed != null && this.x == 0){
            ReimuMod.logger.info("种子做成:" );
            this.x = 1;
            this.seed = new Random(Settings.seed%2);
        }

         */
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int s1;
        if (this.upgraded){
            s1 = AbstractDungeon.cardRandomRng.random(2) + 1;
        }else{
            s1 = AbstractDungeon.cardRandomRng.random(3);
        }
        ReimuMod.logger.info("生成神签: LEVEL:"+s1);
        this.addToBot(new MakeTempCardInHandAction(new Sign(1,s1), 1));
        new setKami(this.magicNumber,"yue");
    }

    public AbstractCard makeCopy() {
        return new SafetyPray();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription =  DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
