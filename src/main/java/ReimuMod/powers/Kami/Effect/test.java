package ReimuMod.powers.Kami.Effect;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeParticleEffect;

public class test extends AbstractGameEffect {
    private float x;
    private float y;

    public test(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x+800.0F, 0.0F,this.x, this.y ));
        AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x-800.0F, 0.0F, this.x, this.y));
        AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(0.0F, this.x+500.0F,this.x, this.y ));
        AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(0.0F, this.x-500.0F,this.x, this.y));
        AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x+600.0F, 0.0F,this.x, this.y));
        AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x-600.0F, 0.0F,this.x, this.y ));
        AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(0.0F, this.x+400.0F,this.x, this.y));
        AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(0.0F, this.x-400.0F,this.x, this.y));
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
