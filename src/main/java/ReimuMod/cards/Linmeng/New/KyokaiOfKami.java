package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.Kami.KyokaiOfKami2Power;
import ReimuMod.powers.Kami.KyokaiOfKamiPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KyokaiOfKami extends CustomCard {
    public static final String ID = "KyokaiOfKami";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 2;
    public KyokaiOfKami() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                AbstractCard.CardTarget.SELF
        );
        this.baseMagicNumber= this.magicNumber = 1 ;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        new setKami(999,"zhi");
        new setKami(999,"yang");
        if (this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new KyokaiOfKami2Power(p,this.magicNumber),this.magicNumber));
        }else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new KyokaiOfKamiPower(p,this.magicNumber),this.magicNumber));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            //this.isInnate = true ;
            //upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
