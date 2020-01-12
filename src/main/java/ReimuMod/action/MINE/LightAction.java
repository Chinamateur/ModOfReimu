package ReimuMod.action.MINE;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import java.util.Iterator;

public class LightAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractMonster m;
    private boolean hitAll;

    public LightAction(DamageInfo info, AbstractMonster m, boolean hitAll) {
        this.info = info;
        this.m = m;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.NONE;
        this.hitAll = hitAll;
    }

    public void update() {
        if (!this.hitAll) {
            AbstractCreature m = this.m;
            if (m != null) {
                float speedTime = 0.4F;
                if (Settings.FAST_MODE) {
                    speedTime = 0.0F;
                }

                this.info.output = AbstractOrb.applyLockOn(m, this.info.base);
                AbstractDungeon.actionManager.addToTop(new DamageAction(m, this.info, AttackEffect.NONE, true));
                AbstractDungeon.actionManager.addToTop(new VFXAction(new LightningEffect(m.drawX, m.drawY), speedTime));
                AbstractDungeon.actionManager.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
        } else {
            float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }

            AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.info.base, true, true), DamageInfo.DamageType.THORNS, AttackEffect.NONE));

            for (AbstractMonster m3 : AbstractDungeon.getMonsters().monsters) {
                if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                    AbstractDungeon.actionManager.addToTop(new VFXAction(new LightningEffect(m3.drawX, m3.drawY), speedTime));
                }
            }

            AbstractDungeon.actionManager.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
        }

        this.isDone = true;
    }}
