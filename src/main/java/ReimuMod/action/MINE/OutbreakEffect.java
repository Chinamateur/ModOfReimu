package ReimuMod.action.MINE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class OutbreakEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float speed;
    private float speedStart;
    private float speedTarget;
    private float stallTimer;
    private Texture img;


    public OutbreakEffect(float x, float y, Color color, float chosenSpeed) {
        this.img = ImageMaster.loadImage("img/ReimuUI/out.png");
        this.stallTimer = MathUtils.random(0.0F, 0.3F);
        this.rotation = MathUtils.random(360.0F);
        this.scale = MathUtils.random(0.5F, 0.9F);
        this.x = x - 128.0F;
        this.y = y - 128.0F;
        this.duration = 2.0F;
        this.color = color;
        this.renderBehind = MathUtils.randomBoolean();
        this.speedStart = chosenSpeed;
        this.speedTarget = 2000.0F * Settings.scale;
        this.speed = this.speedStart;
        color.g -= MathUtils.random(0.1F);
        color.b -= MathUtils.random(0.2F);
        color.a = 0.0F;
    }

    public void update() {
        this.stallTimer -= Gdx.graphics.getDeltaTime();
        if (this.stallTimer < 0.0F) {
            Vector2 tmp = new Vector2(0, 0);
            tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
            tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
            this.speed = Interpolation.fade.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / 2.0F);
            this.scale *= 1.0F + Gdx.graphics.getDeltaTime()  * 5.0F;
            this.duration -= Gdx.graphics.getDeltaTime();
            if (this.duration < 0.0F) {
                this.isDone = true;
            } else if (this.duration > 1.5F) {
                this.color.a = Interpolation.fade.apply(0.0F, 0.7F, (2.0F - this.duration) * 2.0F);
            } else if (this.duration < 0.5F) {
                this.color.a = Interpolation.fade.apply(0.0F, 0.7F, this.duration * 2.0F);
            }
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, 128.0F, 128.0F, 256F, 256F, this.scale, this.scale, this.rotation,0, 0, 256, 256, false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
