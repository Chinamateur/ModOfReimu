package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.cards.KaguraRite;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class CleanRite extends CustomCard {
    public static final String ID = "CleanRite";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;

    public CleanRite() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = 1;
        this.exhaust = true ;
        this.cardsToPreview =new KaguraRite();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReimuMod.logger.info("打出卡牌:"+ID);
        AbstractCard c = new KaguraRite().makeCopy();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new ArtifactPower(p,this.magicNumber),this.magicNumber));
        if (this.upgraded){
            c.upgrade();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c,1,true,true,false));
        }else{
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(c,1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            //this.upgradeDamage(2);
            //this.upgradeMagicNumber(1);
            this.upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
