package ReimuMod.action.MINE;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class OrbThrowaction extends AbstractGameAction {
    private static final float DURATION = 0.01F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private int numTimes;
    private int amount;
    private Color c;

    public OrbThrowaction(AbstractCreature target, int amount, int numTimes,Color C) {
        this.target = target;
        this.actionType = ActionType.DEBUFF;
        this.duration = 0.01F;
        this.numTimes = numTimes;
        this.amount = amount;
        this.c = C;
    }

    public void update() {
        if (this.target == null) {
            this.isDone = true;
        } else if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
        } else {
            if (this.target.currentHealth > 0) {
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_LIGHT), 0F));
                this.target.damage(new DamageInfo(AbstractDungeon.player,this.amount));
            }
            if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                --this.numTimes;
                AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                if(randomMonster != null){
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new  MyOrbEffect(randomMonster.hb.cX, randomMonster.hb.cY,this.target.hb.cX, this.target.hb.cY+200F,this.c), 0F));
                }
                AbstractDungeon.actionManager.addToBottom(new WaitAction(1F));
                AbstractDungeon.actionManager.addToBottom(new OrbThrowaction(randomMonster, this.amount, this.numTimes,this.c));
            }

            this.isDone = true;
        }
    }

}


