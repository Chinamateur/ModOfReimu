package ReimuMod.action.MINE;

import ReimuMod.ReimuMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class disToHandAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard Card;
    public disToHandAction(AbstractCard card) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, this.p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.Card = card;
        this.amount = 1;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (this.p.discardPile.isEmpty()) {
                this.isDone = true;
                return;
            }

            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            Iterator var2 = this.p.discardPile.group.iterator();

            AbstractCard card;
            while(var2.hasNext()) {
                card = (AbstractCard)var2.next();
                if (card == this.Card) {
                    tmp.addToRandomSpot(card);
                }
            }

            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }
            ReimuMod.logger.info("即将加入手牌");
            for(int i = 0; i < this.amount; ++i) {
                if (!tmp.isEmpty()) {
                    tmp.shuffle();
                    card = tmp.getBottomCard();
                    tmp.removeCard(card);
                    if (this.p.hand.size() == 10) {
                        this.p.createHandIsFullDialog();
                    } else {
                        ReimuMod.logger.info(card+"即将加入手牌");
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0F);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DISCARD_PILE_X;
                        card.current_y = CardGroup.DISCARD_PILE_Y;
                        this.p.discardPile.removeCard(card);
                        AbstractDungeon.player.hand.addToTop(card);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                    }
                }
            }

            this.isDone = true;
        }

        this.tickDuration();
    }
}
