package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.OctopathBindingBorderPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OctopathBindingBorder2 extends CustomCard {
    public static final String ID = "OctopathBindingBorder2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 1;
    public OctopathBindingBorder2() {
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
        this.baseMagicNumber= this.magicNumber = 2 ;
        //this.baseDamage= 9 ;
        //this.exhaust =true ;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new OctopathBindingBorderPower(p,this.magicNumber),this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.upgradeBlock(1);
            this.upgradeMagicNumber(1);
            //this.baseDamage = 14;
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            initializeDescription();
        }
    }
}
