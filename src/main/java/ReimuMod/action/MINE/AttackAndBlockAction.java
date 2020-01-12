package ReimuMod.action.MINE;

import ReimuMod.ReimuMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class AttackAndBlockAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.1F;

    public AttackAndBlockAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.target = target;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = DURATION;
    }

    public void update() {
        if (this.target != null) {
            ReimuMod.logger.info("攻击并格挡Action发动！目标:"+this.target);

            AbstractMonster mon = (AbstractMonster) this.target;

            int tmp = mon.currentHealth;

            this.target.damage(this.info);

            int res;

            if (mon.isDying) {
                res = tmp;
            } else {
                res = tmp - mon.currentHealth;
            }

            if (res > 0) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player,AbstractDungeon.player,res));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        this.isDone=true;
        tickDuration();
    }
}
