package ReimuMod.action.MINE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class LightningEffect extends AbstractGameEffect {
    private Texture img ;
    private int index = 0;
    private float x;
    private float y;
    private boolean flipX;
    private boolean flipY;
    private float intervalDuration;

    public LightningEffect(float x, float y, Color mc) {
        this.renderBehind = MathUtils.randomBoolean();
        this.x = x;
        this.y = y;
        this.color = mc;
        this.img = ImageMaster.LIGHTNING_PASSIVE_VFX.get(this.index);
        this.scale = MathUtils.random(0.6F, 1.0F) * Settings.scale;
        this.rotation = MathUtils.random(360.0F);
        if (this.rotation < 120.0F) {
            this.renderBehind = true;
        }

        this.flipX = MathUtils.randomBoolean();
        this.flipY = MathUtils.randomBoolean();
        this.intervalDuration = MathUtils.random(0.03F, 0.06F);
        this.duration = this.intervalDuration;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            ++this.index;
            if (this.index > ImageMaster.LIGHTNING_PASSIVE_VFX.size() - 1) {
                this.isDone = true;
                return;
            }

            this.img = ImageMaster.LIGHTNING_PASSIVE_VFX.get(this.index);
            this.duration = this.intervalDuration;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.x - 61.0F, this.y - 61.0F, 61.0F, 61.0F, 122.0F, 122.0F, this.scale, this.scale, this.rotation, 0, 0, 122, 122, this.flipX, this.flipY);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
