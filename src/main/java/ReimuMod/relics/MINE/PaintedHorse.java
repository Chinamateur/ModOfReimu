package ReimuMod.relics.MINE;

import ReimuMod.ReimuMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class PaintedHorse extends CustomRelic {
    public static AbstractCard c = null;
    public AbstractRelic makeCopy() { return new PaintedHorse(); }
    public static final String NAME = "PaintedHorse";
    private static final int Turn = 2;

    public PaintedHorse() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/PaintedHorse.png"),
                ImageMaster.loadImage("img/relics/outline/PaintedHorse.png"),
                RelicTier.SPECIAL,
                LandingSound.SOLID
        );
        this.counter = Turn ;
    }
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+Turn+DESCRIPTIONS[1];
    }
    public void atBattleStart() {
        c = null ;
        this.counter = 2;
        AbstractDungeon.actionManager.addToTop(new PaintedAction());
    }
    public void onVictory() {
        this.counter = -1;
    }
    public void justEnteredRoom(AbstractRoom room){
        this.counter = -1;
    }

    public void atTurnStart() {
        if(c != null){
            if(this.counter>1){
                this.counter -- ;
            }else {
                this.counter = -1 ;
                this.flash();
                ReimuMod.logger.info("绘马准备将"+c+"置入手牌");
                if(c.cost!=-2){
                    c.costForTurn = 0 ;
                }
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1, false));
                c = null ;
            }
        }
    }
/*
    public void update() {
        super.update();
        if (!this.isDone){
            if (AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                AbstractDungeon.gridSelectScreen.open(p.drawPile,1, CardCrawlGame.languagePack.getPowerStrings("TEXT:ReiMu").NAME, false, false, false, true);
            }else {
                this.c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                //p.drawPile.removeCard(this.c);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.isDone = true;
            }
        }
    }

 */
}
