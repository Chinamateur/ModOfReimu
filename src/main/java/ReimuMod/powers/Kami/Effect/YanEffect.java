package ReimuMod.powers.Kami.Effect;

import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YanEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private Texture img ;
    private String ka ;
    public  YanEffect(float x,float y,int id) {
        this.color = setKami.Kami10.KamiColoer(id);
        this.duration = 0.5F;
        this.scale = 0.7F ;
        this.x = x;
        this.y = y;
        this.ka = setKami.kami[id];
        this.img = ImageMaster.loadImage("img/Reimurelics/kamiorb"+id+".png");
    }

    public void update() {
        //this.rotation += Gdx.graphics.getDeltaTime() * this.vY;
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true ;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img,  this.x- 48.0F, this.y- 48.0F,  48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
        sb.draw(this.img,  this.x- 48.0F, this.y- 48.0F,  48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
        //sb.draw(this.img,  this.x- 48.0F, this.y- 48.0F,  48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
