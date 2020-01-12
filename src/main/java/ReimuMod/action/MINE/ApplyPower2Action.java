package ReimuMod.action.MINE;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ApplyPower2Action extends AbstractGameAction {
    private AbstractCard card;
    public ApplyPower2Action(AbstractCard c) {
        this.card = c;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        this.card.applyPowers();
        this.isDone = true;
    }
}
