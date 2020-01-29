package ReimuMod.action.MINE;

import ReimuMod.ReimuMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class TypeDisToHandAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer p;
    private  CardGroup S ;
    private boolean free ;

    public TypeDisToHandAction(int amount, CardGroup s) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.S = s ;
        this.free = false ;
    }
    public TypeDisToHandAction(int amount,CardGroup s,boolean f) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.S = s ;
        this.free = f ;
    }

    public void update() {
        ReimuMod.logger.info("深山行动：开始更新。");
        if (this.p.hand.size() >= 10) {
            ReimuMod.logger.info("深山行动：手牌满了，跳出。");
            this.isDone = true;
            this.tickDuration();
        } else if (this.S.size() == 1) {
            ReimuMod.logger.info("深山行动：只有唯一的可选目标。");
            AbstractCard card = this.S.group.get(0);
            if (this.free){
                card.costForTurn = 0 ;
            }
            if (this.p.hand.size() < 10) {
                ReimuMod.logger.info("深山行动：加入手牌。");
                this.p.hand.addToHand(card);
                this.p.discardPile.removeCard(card);
            }
            card.lighten(false);
            this.p.hand.refreshHandLayout();
            this.isDone = true;
            this.tickDuration();
        } else if (AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            ReimuMod.logger.info("深山行动：多个目标 开启选择。");
            AbstractDungeon.gridSelectScreen.open(this.S, this.amount, TEXT[0], false);
            this.tickDuration();
        } else {
            ReimuMod.logger.info("深山行动：结算。");
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
                AbstractCard c;
                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.free){
                        c.costForTurn = 0 ;
                    }
                    if (this.p.hand.size() < 10) {
                        this.p.hand.addToHand(c);
                        this.p.discardPile.removeCard(c);
                    }
                    c.lighten(false);
                    c.unhover();
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
                for(var1 = this.S.group.iterator(); var1.hasNext(); c.target_y = 0.0F) {
                    c = (AbstractCard)var1.next();
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                    c.triggerOnGlowCheck();
                }
                this.isDone = true;
            }
        this.tickDuration();
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("DiscardPileToHandAction").TEXT;
    }
}
