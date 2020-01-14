package ReimuMod.action.MINE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class MoneyEffect3 extends AbstractGameEffect {
    private int frostCount;
    private boolean flipped ;

    public MoneyEffect3(int frostCount, boolean flipped) {
        this.frostCount = 5 + frostCount;
        this.flipped = flipped;
        if (this.frostCount > 50) {
            this.frostCount = 50;
        }

    }

    public void update() {
        CardCrawlGame.sound.playA("ORB_FROST_CHANNEL", -0.25F - (float)this.frostCount / 200.0F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);
        AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.GOLD));

        for(int i = 0; i < this.frostCount; ++i) {
            AbstractDungeon.effectsQueue.add(new MoneyEffect4(this.frostCount, this.flipped));
        }
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
