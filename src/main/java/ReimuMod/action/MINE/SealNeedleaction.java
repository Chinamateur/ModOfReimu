package ReimuMod.action.MINE;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class SealNeedleaction extends AbstractGameAction {
    private DamageInfo info;
    int time;
    public SealNeedleaction(AbstractCreature target, DamageInfo info,int i) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
        this.time = i ;
    }

    public void update() {
        if (this.time > 0&&this.target != null) {
            this.time--;
            this.target.damage(this.info);
            if (this.target.lastDamageTaken > 0 && !this.target.isDead) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target,AbstractDungeon.player, new StrengthPower(this.target, -1), -1));
            }
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
            }
        }

        if(this.time <= 0){
            this.isDone=true;
        }
        tickDuration();
        //
    }
}

