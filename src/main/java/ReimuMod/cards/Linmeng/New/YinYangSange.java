package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.YinYangSangePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YinYangSange extends CustomCard {
    public static final String ID = "YinYangSange";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 1;
    public YinYangSange() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        //this.baseDamage = 8;
        this.baseMagicNumber= this.magicNumber = 1;
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }

    public AbstractCard makeCopy() {
        return new YinYangSange();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new YinYangSangePower(p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.upgradeMagicNumber(6);
            //this.isEthereal = false;
            //this.upgradeBaseCost(1);
            this.isInnate =true;
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
