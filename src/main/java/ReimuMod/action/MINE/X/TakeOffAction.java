package ReimuMod.action.MINE.X;

import ReimuMod.action.MINE.setKami;
import ReimuMod.powers.Flyfan;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class TakeOffAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    //private boolean upgraded;
    private AbstractPlayer p;
    private int energyOnUse;
    public TakeOffAction(AbstractCard c, int energyOnUse) {
        this.p = AbstractDungeon.player;
        //this.upgraded = c.upgraded;
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
        //if (this.upgraded){ effect += 1; }
        if (effect>0){
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p,p,new Flyfan(p,effect),effect)
            );
            new setKami(effect,"feng");

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
