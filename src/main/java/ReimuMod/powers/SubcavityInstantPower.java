package ReimuMod.powers;

import ReimuMod.action.MINE.FlyEffect;
import ReimuMod.action.MINE.InstantEffect;
import ReimuMod.action.MINE.LightAction;
import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;

public class SubcavityInstantPower extends AbstractPower {
    public static final String NAME = "SubcavityInstantPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean is ;
    private float particleTimer;
    private  static final Color c = setKami.Kami10.KamiColoer(4);
    public SubcavityInstantPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/Reimupowers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_128.png"),0,0,128,128);
        this.is = true ;
        this.particleTimer = 0.05F;
    }

    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (this.is && info.owner != null && info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL && !info.owner.isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new LightAction(new DamageInfo(info.owner, this.amount, DamageInfo.DamageType.THORNS), (AbstractMonster) info.owner, false));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner,this.ID));
            this.is = false;
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
}
