package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PassingAway extends CustomCard {
    public static final String ID = "PassingAway";
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION2 = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/ReBirth.png";
    private static final int COST = 1;

    public PassingAway() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                AbstractCard.CardTarget.SELF
        );
        this.exhaust =true;
        this.baseMagicNumber=this.magicNumber=1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ExhumeAction(this.upgraded));
        //new PassingAwayBAction(this.magicNumber,true);
        //AbstractDungeon.actionManager.addToBottom(new ExhumeAction(this.upgraded));
        //AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, x));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.upgradeBaseCost(0);
            this.rawDescription = DESCRIPTION2;
            initializeDescription();
        }
    }
}
