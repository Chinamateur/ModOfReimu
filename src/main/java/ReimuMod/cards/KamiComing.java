package ReimuMod.cards;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KamiComing extends CustomCard {

    public static final String ID = "KamiComing";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPDESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXDESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 0;
    private int USE;
    public KamiComing(int use) {
        super(
                ID+":ReiMu",
                (use == -1)? NAME : EXDESCRIPTION[use],
                (use == -1)? ("img/cards/KamiComing.png") : ("img/cards/"+ID+use+".png"),
                //"img/cards/KamiComing.png",
                COST,
                (use == -1)? DESCRIPTION : EXDESCRIPTION[use+7],
                CardType.POWER,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.exhaust = true;
        this.USE = use;
        if (this.USE!=-1){
            this.name = EXDESCRIPTION[this.USE];
            this.loadCardImage("img/cards/"+ID+this.USE+".png");
            this.rawDescription = EXDESCRIPTION[this.USE+7];
            ReimuMod.logger.info("降神"+this.USE+"："+this.name+"图像:"+"img/cards/"+ID+this.USE+".png");
            initializeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        String[] kami = {"炎","破","智","月","雷","风","阳"};
        setKami.getKami2 x = new setKami.getKami2();
        ReimuMod.logger.info("降神"+this.USE+"预计获得:"+setKami.kami[this.USE]+(setKami.max() - x.getKami2(setKami.kami[this.USE]))+"层");
        new setKami(setKami.max() - x.getKami2(kami[this.USE]),kami[this.USE]);
        if (this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        }
    }

    public AbstractCard makeCopy() { return new KamiComing(this.USE); }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = EXDESCRIPTION[this.USE+7] + UPDESCRIPTION + EXDESCRIPTION[14] ;
            initializeDescription();
        }
    }
}
