package ReimuMod.relics.MINE;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PaintedAction extends AbstractGameAction {
    private AbstractPlayer p;
    public PaintedAction(){
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    public void update() {
        if (AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(p.drawPile,1, CardCrawlGame.languagePack.getPowerStrings("TEXT:ReiMu").NAME, false, false, false, true);
        }else {
            PaintedHorse.c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            p.drawPile.removeCard(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
        this.tickDuration();
    }
}
