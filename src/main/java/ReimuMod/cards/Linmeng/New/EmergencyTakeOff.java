package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.Flyfan;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class EmergencyTakeOff extends CustomCard {
    public static final String ID = "EmergencyTakeOff";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 0;

    public EmergencyTakeOff() {
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
        //this.baseBlock = 11;
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new VulnerablePower(p,this.magicNumber,false),this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new Flyfan(p,this.magicNumber),this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeBlock(3);
            this.upgradeMagicNumber(1);
            //this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
