package ReimuMod.powers.Kami;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.LightningEffect;
import ReimuMod.action.MINE.MyOrbEffect;
import ReimuMod.action.MINE.setKami;
import ReimuMod.powers.Kami.Effect.YanEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KamiLeiPower extends AbstractPower {
    public static boolean wwe ;
    private static final int KamiID = 2 ;
    private static final String[] DESC2 = CardCrawlGame.languagePack.getPowerStrings("KamiYanOrb").DESCRIPTIONS;
    public static final int number = 15;
    public static final String NAME = "KamiLeiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static float xx;
    private static float yy;
    protected float particleTimer;
    //public static final int maxnumber = 10;
    public KamiLeiPower(AbstractCreature owner, int amount) {
        this.priority = -KamiID-999;
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= setKami.max()) {
            this.amount = setKami.max();
        }
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner,this.ID));
        }
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/Reimupowers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_128.png"),0,0,128,128);
        if (!wwe &&!this.owner.hasPower(this.ID)&&this.amount>=setKami.max()&&!this.owner.hasPower("BlasphemyArrayPower:ReiMu")){
            this.on10();
            wwe = true;
        }
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
            }
        }
    }
    //描述文本
    public void updateDescription() {
        if (AbstractDungeon.player.hasPower("BlasphemyArrayPower:ReiMu")){
            this.description = DESC2[2];
        }else if (this.amount<setKami.max()){
            this.description = DESCRIPTIONS[0] + (setKami.max() - this.amount) + DESCRIPTIONS[1];
        }else{
            this.description = DESCRIPTIONS[2];
        }
    }
/*
    @Override
    public void atStartOfTurn() {
        if (this.amount>9){
            this.flash();
        }
    }
 */
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (!info.owner.hasPower(OutlierPower.NAME+":ReiMu")&&info.owner != null && this.amount>=setKami.max() && info.type == DamageInfo.DamageType.NORMAL && info.owner != this.owner&&!this.owner.hasPower("BlasphemyArrayPower:ReiMu")) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(info.owner,this.owner,new OutlierPower(info.owner)));
        }
        return damageAmount;
    }

    @Override
    public void stackPower(int stackAmount) {
        if (this.amount < setKami.max() && this.amount+stackAmount>=setKami.max()&&!this.owner.hasPower("BlasphemyArrayPower:ReiMu")) { this.on10(); }
        this.amount = Math.min(this.amount+stackAmount,setKami.max());
        updateDescription();
    }
    //集齐10点
    private void on10(){
        this.flash();
        //AbstractDungeon.actionManager.addToBottom(new IncreaseMaxOrbAction(1));
        new setKami.Kami10(KamiID);
        //AbstractDungeon.actionManager.addToBottom(new ChannelAction(new KamiLeiOrb()));
        for (AbstractOrb o : AbstractDungeon.player.orbs){
            if (o!=null){
                o.updateDescription();
            }
        }
        CardCrawlGame.sound.play("ORB_PLASMA_EVOKE", 0.1F);
        updateDescription();
    }
    @Override
    public void reducePower(int reduceAmount) {
        if (this.amount >= setKami.max()&& this.amount-reduceAmount < setKami.max()&&!this.owner.hasPower("BlasphemyArrayPower:ReiMu")){
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
