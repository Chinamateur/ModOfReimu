package ReimuMod.action.MINE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ShineLinesEffect;

import java.util.Iterator;

public class MoneyEffect5 extends AbstractGameEffect {
    private static final float GRAVITY;
    private static final float START_VY;
    private static final float START_VY_JITTER;
    private static final float START_VX;
    private static final float START_VX_JITTER;
    private static final float TARGET_JITTER;
    private float rotationSpeed;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float targetX;
    private float targetY;
    //private TextureAtlas.AtlasRegion img;
    private Texture img;
    private float alpha;
    private float suctionTimer;
    private float staggerTimer;
    private boolean showGainEffect;
    private AbstractCreature owner;
    private int Once;

    public MoneyEffect5(AbstractCreature owner, float x, float y, float targetX, float targetY, boolean showGainEffect,int once) {
        this.alpha = 0.0F;
        this.Once = once;
        this.suctionTimer = 0.7F;
        /*
        if (MathUtils.randomBoolean()) {
            this.img = ImageMaster.COPPER_COIN_1;
        } else {
            this.img = ImageMaster.COPPER_COIN_2;
        }

         */
        //this.img = ImageMaster.loadImage("img/ReimuUI/money/"+(int)(Math.random()*10)+".png");

        this.x = x - 10F;
        this.y = y - 10F;
        this.targetX = targetX + MathUtils.random(-TARGET_JITTER, TARGET_JITTER);
        this.targetY = targetY + MathUtils.random(-TARGET_JITTER, TARGET_JITTER * 2.0F);
        this.showGainEffect = showGainEffect;
        this.owner = owner;
        this.staggerTimer = MathUtils.random(0.0F, 0.5F);
        this.vX = MathUtils.random(START_VX - 50.0F * Settings.scale, START_VX_JITTER);
        this.rotationSpeed = MathUtils.random(1000.0F, 4000.0F);
        if (MathUtils.randomBoolean()) {
            this.vX = -this.vX;
            this.rotationSpeed = -this.rotationSpeed;
        }

        this.vY = MathUtils.random(START_VY, START_VY_JITTER);
        this.scale = Settings.scale;
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
    }

    public MoneyEffect5(AbstractCreature owner,int once) {
        this.alpha = 0.0F;
        this.Once = once;
        this.suctionTimer = 0.7F;
        /*
        if (MathUtils.randomBoolean()) {
            this.img = ImageMaster.COPPER_COIN_1;
        } else {
            this.img = ImageMaster.COPPER_COIN_2;
        }

         */

        this.x = AbstractDungeon.player.hb.cX - 10F;
        this.y = AbstractDungeon.player.hb.cY - 10F;
        this.targetX = AbstractDungeon.player.hb.cX+(int)(Math.random()*(Settings.WIDTH / 4F))-(Settings.WIDTH / 10F);
        this.targetY = AbstractDungeon.player.hb.cY-(Settings.HEIGHT / 13F)-(int)(Math.random()*(Settings.HEIGHT / 30F));
        this.showGainEffect = true;
        this.owner = owner;
        this.vX = MathUtils.random(this.targetX/4, this.targetX/2);
        this.rotationSpeed = MathUtils.random(500.0F, 2000.0F);
        if (MathUtils.randomBoolean()) {
            this.vX = -this.vX;
            this.rotationSpeed = -this.rotationSpeed;
        }
        this.staggerTimer = MathUtils.random(0.0F, 0.5F);
        this.vY = MathUtils.random(START_VY, START_VY_JITTER);
        this.scale = Settings.scale;
        this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
    }

    public void update() {
        if (this.staggerTimer > 0.0F) {
            this.staggerTimer -= Gdx.graphics.getDeltaTime();
        } else {
            if (this.alpha != 1.0F) {
                this.alpha += Gdx.graphics.getDeltaTime() * 2.0F;
                if (this.alpha > 1.0F) {
                    this.alpha = 1.0F;
                }

                this.color.a = this.alpha;
            }

            this.rotation += Gdx.graphics.getDeltaTime() * this.rotationSpeed;
            this.x += Gdx.graphics.getDeltaTime() * this.vX;
            this.y += Gdx.graphics.getDeltaTime() * this.vY;
            this.vY -= Gdx.graphics.getDeltaTime() * GRAVITY;
            if (this.suctionTimer > 0.0F) {
                this.suctionTimer -= Gdx.graphics.getDeltaTime();
            } else {
                this.vY = MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
                this.vX = MathUtils.lerp(this.vX, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
                this.x = MathUtils.lerp(this.x, this.targetX, Gdx.graphics.getDeltaTime() * 4.0F);
                this.y = MathUtils.lerp(this.y, this.targetY, Gdx.graphics.getDeltaTime() * 4.0F);
                if (Math.abs(this.x - this.targetX) < 20.0F) {
                    this.isDone = true;
                    if (MathUtils.randomBoolean()) {
                        CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
                    }
                    //AbstractDungeon.player.gold += this.Once;
                    //AbstractDungeon.player.gainGold(this.Once);

                    AbstractDungeon.effectsQueue.add(new ShineLinesEffect(this.x, this.y));
                    boolean textEffectFound = false;
                    Iterator var2 = AbstractDungeon.effectList.iterator();

                    AbstractGameEffect e;
                    while(var2.hasNext()) {
                        e = (AbstractGameEffect)var2.next();
                        if (e instanceof MoneyEffect2 && ((MoneyEffect2)e).ping(this.Once)) {
                            textEffectFound = true;
                            break;
                        }
                    }

                    if (!textEffectFound) {
                        var2 = AbstractDungeon.effectsQueue.iterator();

                        while(var2.hasNext()) {
                            e = (AbstractGameEffect)var2.next();
                            if (e instanceof MoneyEffect2 && ((MoneyEffect2)e).ping(this.Once)) {
                                textEffectFound = true;
                            }
                        }
                    }

                    if (!textEffectFound && this.showGainEffect) {
                        AbstractDungeon.effectsQueue.add(new MoneyEffect2(this.Once));
                    }
                }
            }

        }
    }

    public void render(SpriteBatch sb) {
        if (this.staggerTimer <= 0.0F) {
            sb.setColor(this.color);
            //sb.draw(this.img, this.x, this.y, 10F, 10F, 20F, 20F, this.scale, this.scale, this.rotation,0, 0, 20, 20, false, false);
        }
    }

    public void dispose() {
    }

    static {
        GRAVITY = 2000.0F * Settings.scale;
        START_VY = 800.0F * Settings.scale;
        START_VY_JITTER = 400.0F * Settings.scale;
        START_VX = 200.0F * Settings.scale;
        START_VX_JITTER = 300.0F * Settings.scale;
        TARGET_JITTER = 50.0F * Settings.scale;
    }
}
