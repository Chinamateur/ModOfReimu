package ReimuMod.action.MINE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.IceShatterEffect;

public class MoneyEffect4 extends AbstractGameEffect {
    private float waitTimer;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float floorY;
    private Texture img;
    private int frostCount;

    MoneyEffect4(int frostCount, boolean flipped) {
        this.frostCount = frostCount;
        this.img = ImageMaster.loadImage("img/ReimuUI/money/"+(int)(Math.random()*10)+".png");

        this.waitTimer = MathUtils.random(0.0F, 0.5F);
        if (flipped) {
            this.x = MathUtils.random(420.0F, 1420.0F) * Settings.scale - 48.0F;
            this.vX = MathUtils.random(-600.0F, -900.0F);
            this.vX += (float)frostCount * 5.0F;
        } else {
            this.x = MathUtils.random(500.0F, 1500.0F) * Settings.scale - 48.0F;
            this.vX = MathUtils.random(600.0F, 900.0F);
            this.vX -= (float)frostCount * 5.0F;
        }

        this.y = (float)Settings.HEIGHT + MathUtils.random(100.0F, 300.0F) - 48.0F;
        this.vY = MathUtils.random(2500.0F, 4000.0F);
        this.vY -= (float)frostCount * 10.0F;
        this.vY *= Settings.scale;
        this.vX *= Settings.scale;
        this.duration = 2.0F;
        this.scale = MathUtils.random(0.75F, 1.25F);
        this.scale += (float)frostCount * 0.04F;
        this.vX *= this.scale;
        this.scale *= Settings.scale;
        this.color = new Color(0.9F, 0.9F, 1.0F, MathUtils.random(0.9F, 1.0F));
        Vector2 derp = new Vector2(this.vX, this.vY);
        if (flipped) {
            this.rotation = derp.angle() + 225.0F - (float)frostCount / 3.0F;
        } else {
            this.rotation = derp.angle() - 45.0F + (float)frostCount / 3.0F;
        }

        this.renderBehind = MathUtils.randomBoolean();
        this.floorY = AbstractDungeon.floorY + MathUtils.random(-200.0F, 50.0F) * Settings.scale;
    }

    public void update() {
        this.waitTimer -= Gdx.graphics.getDeltaTime();
        if (this.waitTimer <= 0.0F) {
            this.x += this.vX * Gdx.graphics.getDeltaTime();
            this.y -= this.vY * Gdx.graphics.getDeltaTime();
            if (this.y < this.floorY) {
                float pitch = 0.8F;
                pitch -= (float)this.frostCount * 0.025F;
                pitch += MathUtils.random(-0.2F, 0.2F);
                CardCrawlGame.sound.playA("ORB_FROST_EVOKE", pitch);

                for(int i = 0; i < 4; ++i) {
                    AbstractDungeon.effectsQueue.add(new IceShatterEffect(this.x, this.y));
                }

                this.isDone = true;
            }

        }
    }

    public void render(SpriteBatch sb) {
        if (this.waitTimer < 0.0F) {
            sb.setBlendFunction(770, 1);
            sb.setColor(this.color);
            sb.draw(this.img, this.x, this.y, 10F, 10F, 20F, 20F, this.scale, this.scale, this.rotation,0, 0, 20, 20, false, false);
            sb.setBlendFunction(770, 771);
        }

    }

    public void dispose() {
    }
}
