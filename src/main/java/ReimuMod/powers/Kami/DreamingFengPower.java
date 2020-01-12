package ReimuMod.powers.Kami;

import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;

public class DreamingFengPower extends AbstractPower {
    public static final String NAME = "DreamingFengPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean is ;
    private float particleTimer2 = MathUtils.random(0.45F, 0.55F);
    public DreamingFengPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/powers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_128.png"),0,0,128,128);
        this.is = true ;
    }

    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void updateParticles() {
        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Calm"));
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (this.is && info.owner != null && info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL && !info.owner.isPlayer) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(info.owner,new DamageInfo(info.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner,this.ID));
            this.is = false;
        }
        return damageAmount;
    }
    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return this.calculateDamageTakenAmount(damage, type);
    }

    private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type) {
        return (this.is&&this.amount>=0 && type == DamageInfo.DamageType.NORMAL) ?   0 : damage;
    }
}
