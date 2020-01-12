package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.DependentFormPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DependentForm extends CustomCard {
    public static final String ID = "DependentForm";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DependentForm.png";
    private static final int COST = 3;
    public DependentForm() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        //this.baseDamage = 8;
        this.baseMagicNumber= this.magicNumber = 4;
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }
    public AbstractCard makeCopy() {
        return new DependentForm();
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DependentFormPower(p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(2);
            //this.isEthereal = false;
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
