package ReimuMod.cards;

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
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class KaguraRite extends CustomCard {

    public static final String ID = "KaguraRite";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPDESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXDESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 1;
    public KaguraRite() {
        super(
                ID+":ReiMu",
                NAME,
                "img/cards/"+ID+".png",
                COST,
                DESCRIPTION,
                CardType.SKILL,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.exhaust = true ;
        this.baseMagicNumber = this.magicNumber = 10;
        this.cardsToPreview = new ComingRite();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new VigorPower(p,this.magicNumber)));
        AbstractCard c = new ComingRite().makeCopy();
        if (this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c,1,true,true,false));
        }else {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(c,1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(5);
            this.cardsToPreview.upgrade();
            this.rawDescription = UPDESCRIPTION;
            initializeDescription();
        }
    }
}
