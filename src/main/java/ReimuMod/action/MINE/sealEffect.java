package ReimuMod.action.MINE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class sealEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private float scaleX;
    private float scaleY;
    private float targetScale;
    private Color color2;
    private AbstractMonster target ;
    private DamageInfo info;
    public sealEffect(AbstractMonster A,DamageInfo I) {
        this.info = I;
        this.target = A ;
        this.x = (A.hb.cX) - 64.0F - 500.0F / 2.0F * Settings.scale;
        this.y = (A.hb.cY - 30.0F * Settings.scale) - 64.0F + 200.0F / 2.0F * Settings.scale;
        this.sX = this.x;
        this.sY = this.y;
        this.tX = this.x + 500.0F / 2.0F * Settings.scale;
        this.tY = this.y + -200.0F / 2.0F * Settings.scale;
        this.color = Color.WHITE.cpy();
        this.color2 = Color.PINK.cpy();
        this.color.a = 0.0F;
        this.startingDuration = 0.4F;
        this.duration = this.startingDuration;
        this.targetScale = 3.0F;
        this.scaleX = 0.01F;
        this.scaleY = 0.01F;
        this.rotation = -135.0F;
        this.rotation = 250.0F;
    }


    public void update() {
        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = Interpolation.exp10In.apply(0.8F, 0.0F, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
            this.scaleX = Interpolation.exp10In.apply(this.targetScale, 0.1F, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
            this.scaleY = this.scaleX;
            this.x = Interpolation.fade.apply(this.tX, this.sX, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
            this.y = Interpolation.fade.apply(this.tY, this.sY, (this.duration - this.startingDuration / 2.0F) / (this.startingDuration / 2.0F));
        } else {
            this.scaleX = Interpolation.pow2In.apply(0.5F, this.targetScale, this.duration / (this.startingDuration / 2.0F));
            this.color.a = Interpolation.pow5In.apply(0.0F, 0.8F, this.duration / (this.startingDuration / 2.0F));
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            if(this.target != null){
                this.target.damage(this.info);
                if (this.target.lastDamageTaken > 0 && !this.target.isDead) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target,AbstractDungeon.player, new StrengthPower(this.target, -1), -1));
                }
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
                }
            }
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color2);
        sb.setBlendFunction(770, 1);
        sb.draw(ImageMaster.ANIMATED_SLASH_VFX, this.x, this.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scaleX * 0.4F * MathUtils.random(0.95F, 1.05F) * Settings.scale, this.scaleY * 0.7F * MathUtils.random(0.95F, 1.05F) * Settings.scale, this.rotation, 0, 0, 128, 128, false, false);
        sb.setColor(this.color);
        sb.draw(ImageMaster.ANIMATED_SLASH_VFX, this.x, this.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scaleX * 0.7F * MathUtils.random(0.95F, 1.05F) * Settings.scale, this.scaleY * MathUtils.random(0.95F, 1.05F) * Settings.scale, this.rotation, 0, 0, 128, 128, false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}

