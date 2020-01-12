package ReimuMod.powers.Kami.Effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class broEffect extends AbstractGameEffect {
    private TextureAtlas.AtlasRegion img;
    private static final float DUR = 1.0F;
    private boolean additive;
    private float x;
    private float y;
    public broEffect(Color color) {
        this(color, true,(float)Settings.WIDTH, (float)Settings.HEIGHT );
    }

    public broEffect(Color color, boolean additive,float X ,float Y) {
        this.img = ImageMaster.BORDER_GLOW_2;
        this.duration = 1.0F;
        this.color = color.cpy();
        this.color.a = 0.0F;
        this.additive = additive;
        this.x = X ;
        this.y = Y ;
    }

    public void update() {
        if (1.0F - this.duration < 0.1F) {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (1.0F - this.duration) * 10.0F);
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        if (this.additive) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.color);
            sb.draw(this.img, 0.0F, 0.0F,this.x, this.y);
            sb.setBlendFunction(770, 771);
        } else {
            sb.setColor(this.color);
            sb.draw(this.img, 0.0F, 0.0F,this.x, this.y);
        }

    }

    public void dispose() {
    }
}
