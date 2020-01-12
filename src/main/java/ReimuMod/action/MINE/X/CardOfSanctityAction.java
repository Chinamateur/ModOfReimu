package ReimuMod.action.MINE.X;

import ReimuMod.action.MINE.setKami;
import ReimuMod.cards.Sign;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class CardOfSanctityAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private boolean upgraded;
    private AbstractPlayer p;
    private int energyOnUse;

    public CardOfSanctityAction(AbstractCard c, int energyOnUse) {
        this.p = AbstractDungeon.player;
        this.upgraded = c.upgraded;
        this.freeToPlayOnce = c.freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {

            new setKami(effect,"roll");
            while (effect > 0){
                int s1 = AbstractDungeon.cardRng.random(3)+1;
                int s2 = AbstractDungeon.cardRng.random(2)+1;
                AbstractCard c = new Sign(s1,s2);
                if (this.upgraded) {
                    c.upgrade();
                }
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c, 1));
                effect-- ;
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
