package ReimuMod.powers.Kami;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.LightningEffect;
import ReimuMod.action.MINE.MyOrbEffect;
import ReimuMod.action.MINE.setKami;
import ReimuMod.powers.Kami.Effect.MylightEffect;
import ReimuMod.powers.Kami.Effect.YanEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class KamiYanPower extends AbstractPower {
    public static boolean wwe ;
    public static final int KamiID = 0 ;
    public static final String NAME = "KamiYanPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int number = 1;
    protected float particleTimer;
    private static float xx;
    private static float yy;
    //public static final int maxnumber = 10;
    public KamiYanPower (AbstractCreature owner, int amount) {
        this.priority = -KamiID-999;
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/Reimupowers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_128.png"),0,0,128,128);
        if (!wwe && !this.owner.hasPower("KamiYanPower:ReiMu") && this.amount>=setKami.max()){
            wwe = true;
            this.on10();
        }
        this.particleTimer = 1F;
        xx = setKami.Kami10.setSob(KamiID,1);
        yy = setKami.Kami10.setSob(KamiID,0);
        this.particleTimer = 0.5F;
    }
    public void updateParticles() {
        if (!Settings.DISABLE_EFFECTS && this.amount>= setKami.max()) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.5F;
                AbstractDungeon.effectsQueue.add(new YanEffect(xx, yy,KamiID));
                AbstractDungeon.effectList.add(new LightningEffect(xx,yy,setKami.Kami10.KamiColoer(KamiID)));
                if (MathUtils.randomBoolean()) { AbstractDungeon.effectList.add(new LightningEffect(xx,yy,setKami.Kami10.KamiColoer(KamiID))); }
                //AbstractDungeon.effectsQueue.add(new YanEffect(xx, yy,KamiID));
            }
        }
    }
    //描述文本
    public void updateDescription() {
        if (this.amount<setKami.max()){
            this.description = DESCRIPTIONS[0] + (setKami.max() - this.amount) + DESCRIPTIONS[1];
        }else{
            this.description = DESCRIPTIONS[2] + (number*this.amount) + DESCRIPTIONS[3];
        }
    }

        @Override
        public void atStartOfTurn() {
            if (this.amount>=setKami.max()){
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.amount*number, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            }
        }


    @Override
    public void stackPower(int stackAmount) {
        if (this.amount < setKami.max() && this.amount+stackAmount>=setKami.max()) { this.on10(); }
        this.amount += stackAmount;
        updateDescription();
    }
    //集齐10点
    public  void on10(){
        this.flash();
        //AbstractDungeon.actionManager.addToBottom(new IncreaseMaxOrbAction(1));
        new setKami.Kami10(KamiID);
        //AbstractDungeon.actionManager.addToBottom(new ChannelAction(new KamiYanOrb()));
        if (!Settings.DISABLE_EFFECTS) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect( setKami.Kami10.KamiColoer(KamiID), true)));
        }
        for (AbstractOrb o : AbstractDungeon.player.orbs){
            if (o!=null){
                o.updateDescription();
            }
        }
        updateDescription();
    }
    @Override
    public void reducePower(int reduceAmount) {
        if (this.amount >= setKami.max()&& this.amount-reduceAmount < setKami.max()){
            ReimuMod.logger.info("跌落神坛！"+KamiID);
            onRemove();
        }
        super.reducePower(reduceAmount);
    }
    public void onRemove() {
        //AbstractDungeon.actionManager.addToBottom(new VFXAction(new MylightEffect(KamiID,true)));
        wwe = false;
    }
}
