package ReimuMod.powers.Kami;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.MyOrbEffect;
import ReimuMod.action.MINE.setKami;
import ReimuMod.powers.Kami.Effect.MylightEffect;
import ReimuMod.powers.Kami.Effect.YanEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;

public class KamiYangPower extends AbstractPower {
    public static boolean wwe ;
    public static final int KamiID = 6 ;
    private static final String[] DESC2 = CardCrawlGame.languagePack.getPowerStrings("KamiYanOrb").DESCRIPTIONS;
    public static final String NAME = "KamiYangPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int number = 2;
    private static float xx;
    private static float yy;
    protected float particleTimer;
    protected float particleTimer2;
    //public static final int maxnumber = 10;
    public KamiYangPower(AbstractCreature owner, int amount) {
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
        //this.img = new Texture("img/powers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_128.png"),0,0,128,128);
        if (!wwe&&!this.owner.hasPower(this.ID)&&this.amount>=setKami.max()&&!this.owner.hasPower("BlasphemyArrayPower:ReiMu")){
            this.on10();
            wwe = true;
        }
        xx = setKami.Kami10.setSob(KamiID,1);
        yy = setKami.Kami10.setSob(KamiID,0);
        this.particleTimer = 1F;
        this.particleTimer2 = 1F;
    }
    public void updateParticles() {
        if (!Settings.DISABLE_EFFECTS && this.amount>= setKami.max()) {
            this.particleTimer2 -= Gdx.graphics.getDeltaTime();
       // Gdx.graphics.getDeltaTime();
            if(this.particleTimer2 < 0){
                this.particleTimer2 = 0.5F;
                if (MathUtils.randomBoolean()) { AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(xx,yy)); }
                AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(xx,yy));
                AbstractDungeon.effectsQueue.add(new YanEffect(xx, yy,KamiID));
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
            this.description = DESCRIPTIONS[2] + number + DESCRIPTIONS[3];
        }
    }
    @Override
    public void stackPower(int stackAmount) {
        if (this.amount < setKami.max() && this.amount+stackAmount>=setKami.max()&&!this.owner.hasPower("BlasphemyArrayPower:ReiMu")) { this.on10(); }
        this.amount = Math.min(this.amount+stackAmount,setKami.max());
        updateDescription();
    }

    public void onEnergyRecharge() {
        if (this.amount>=setKami.max()&&!this.owner.hasPower("BlasphemyArrayPower:ReiMu")){
            this.flash();
            AbstractDungeon.player.gainEnergy(number);
        }
    }


    //集齐10点
    public  void on10(){
        this.flash();
        //AbstractDungeon.actionManager.addToTop(new IncreaseMaxOrbAction(1));
        new setKami.Kami10(KamiID);

        //AbstractDungeon.actionManager.addToBottom(new ChannelAction(new KamiYangOrb()));
        for (AbstractOrb o : AbstractDungeon.player.orbs){
            if (o!=null){
                o.updateDescription();
            }
        }
        //CardCrawlGame.sound.play("ORB_PLASMA_EVOKE", 0.1F);
        //CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F);
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
    public void onRemove()
    {
        //AbstractDungeon.actionManager.addToBottom(new VFXAction(new MylightEffect(KamiID,true)));
        wwe = false;
    }

}
