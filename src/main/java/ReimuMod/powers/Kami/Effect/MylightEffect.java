package ReimuMod.powers.Kami.Effect;


import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

public class MylightEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private TextureAtlas.AtlasRegion img;
    private Color altColor;
    private String sfxUrl = "HEAL_3";

    public MylightEffect(int x) {
        if (this.img == null) {
            this.img = ImageMaster.CRYSTAL_IMPACT;
        }
        this.x = setKami.Kami10.setSob(x,1) - (float)this.img.packedWidth / 2.0F;
        this.y = setKami.Kami10.setSob(x,0)- (float)this.img.packedHeight / 2.0F;
        this.startingDuration = 0.7F;
        this.duration = this.startingDuration;
        this.scale = Settings.scale;
        this.altColor = setKami.Kami10.KamiColoer2(x);
        this.color = setKami.Kami10.KamiColoer(x);
        this.color.a = 0.0F;
        this.renderBehind = false;
    }
    public MylightEffect(int x,boolean zzz) {
        if (this.img == null) {
            this.img = ImageMaster.CRYSTAL_IMPACT;
        }
        Color c = setKami.Kami10.KamiColoer(x);
        c.r /= 2;
        c.b /= 2;
        c.g /= 2;
        this.x = setKami.Kami10.setSob(x,1) - (float)this.img.packedWidth / 2.0F;
        this.y = setKami.Kami10.setSob(x,0)- (float)this.img.packedHeight / 2.0F;
        this.startingDuration = 0.7F;
        this.duration = this.startingDuration;
        this.scale = Settings.scale;
        this.altColor = setKami.Kami10.KamiColoer2(x);
        this.color = c;
        this.color.a = 0.0F;
        this.renderBehind = false;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            CardCrawlGame.sound.playA(this.sfxUrl, 0.5F);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
        } else {
            this.color.a = Interpolation.fade.apply(0.01F, 1.0F, this.duration / (this.startingDuration / 2.0F)) * Settings.scale;
        }

        this.scale = Interpolation.pow5In.apply(2.4F, 0.3F, this.duration / this.startingDuration) * Settings.scale;
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        this.altColor.a = this.color.a;
        sb.setColor(this.altColor);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 1.1F, this.scale * 1.1F, 0.0F);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.9F, this.scale * 0.9F, 0.0F);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
