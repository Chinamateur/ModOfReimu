package ReimuMod.action.MINE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MoneyEffect2 extends AbstractGameEffect {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private static int totalGold;
    private int gold ;
    private boolean reachedCenter;
    private float x;
    private float y;
    private float destinationY;
    private float waitTimer = 1.0F;
    private float fadeTimer = 1.0F;
    private static final float FADE_Y_SPEED;

    MoneyEffect2(int startingAmount) {
        this.x = AbstractDungeon.player.hb.cX;
        this.y = AbstractDungeon.player.hb.cY;
        this.destinationY = this.y + 150.0F * Settings.scale;
        this.duration = 3.0F;
        this.startingDuration = 3.0F;
        this.reachedCenter = false;
        this.gold = startingAmount;
        totalGold = startingAmount;
        this.color = Color.GOLD.cpy();
    }

    public void update() {
        if (this.waitTimer > 0.0F) {
            this.gold = totalGold;
            if (!this.reachedCenter && this.y != this.destinationY) {
                this.y = MathUtils.lerp(this.y, this.destinationY, Gdx.graphics.getDeltaTime() * 9.0F);
                if (Math.abs(this.y - this.destinationY) < Settings.UI_SNAP_THRESHOLD) {
                    this.y = this.destinationY;
                    this.reachedCenter = true;
                }
            } else {
                this.waitTimer -= Gdx.graphics.getDeltaTime();
                if (this.waitTimer < 0.0F) {
                    this.gold = totalGold;
                } else {
                    this.gold = totalGold;
                }
            }
        } else {
            this.y += Gdx.graphics.getDeltaTime() * FADE_Y_SPEED;
            this.fadeTimer -= Gdx.graphics.getDeltaTime();
            this.color.a = this.fadeTimer;
            if (this.fadeTimer < 0.0F) {
                this.isDone = true;
            }
        }

    }

    boolean ping(int amount) {
        if (this.waitTimer > 0.0F) {
            this.waitTimer = 1.0F;
            totalGold += amount;
            return true;
        } else {
            return false;
        }
    }

    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            if (this.gold>0){
                FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, "+ " + this.gold + TEXT[0], this.x, this.y, this.color);
            }else if (this.gold<0){
                FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.gold + TEXT[0], this.x, this.y, this.color);
            }

        }

    }

    public void dispose() {
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("GainGoldTextEffect");
        TEXT = uiStrings.TEXT;
        totalGold = 0;
        FADE_Y_SPEED = 100.0F * Settings.scale;
    }
}
