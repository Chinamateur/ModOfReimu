package ReimuMod.powers.Kami.Effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightRayFlyOutEffect;

public class MedEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vfxTimer;
    private int count = 10;
    private Color car ;

    public MedEffect(float newX, float newY,Color c) {
        this.x = newX;
        this.y = newY;
        this.car = c;
    }

    public void update() {
        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F) {
            --this.count;
            this.vfxTimer = MathUtils.random(0.0F, 0.02F);

            for(int i = 0; i < 3; ++i) {
                AbstractDungeon.effectsQueue.add(new LightRayFlyOutEffect(this.x, this.y, this.car));
            }
        }

        if (this.count <= 0) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
