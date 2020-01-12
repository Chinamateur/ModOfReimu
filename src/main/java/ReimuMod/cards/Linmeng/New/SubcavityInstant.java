package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.SubcavityInstantPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SubcavityInstant extends CustomCard {
    public static final String ID = "SubcavityInstant";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;
    public SubcavityInstant() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        //this.baseDamage = 8;
        this.baseMagicNumber= this.magicNumber = 12;
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SubcavityInstantPower(p, this.magicNumber), this.magicNumber));
        new setKami(2,"lei");
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.upgradeMagicNumber(2);
            //this.isEthereal = false;
            this.upgradeMagicNumber(4);
            //this.isInnate =true;
            //this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
