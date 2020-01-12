package ReimuMod.powers;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.FlyEffect;
import ReimuMod.action.MINE.disToHandAction;
import ReimuMod.action.MINE.setKami;
import ReimuMod.cards.Linmeng.New.Amulet;
import ReimuMod.patches.decktohandpatch;
import ReimuMod.relics.MINE.Bow_fan;
import ReimuMod.relics.MINE.SkyOrb;
import ReimuMod.relics.MINE.TurtleShell;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
//import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;



public class Flyfan extends AbstractPower {
    //private static final AbstractPlayer p  = AbstractDungeon.player;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flyfan:ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int s1 = 50 ;
    private boolean use = false ;
    private static final int s2 = TurtleShell.s1;
    private boolean isDone = false ;
    protected float particleTimer;
    //protected float particleTimer2;

    public Flyfan(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "Flyfan:ReiMu" ;
        this.owner = owner ;
        this.amount = amount ;
        this.updateDescription() ;
        this.type = AbstractPower.PowerType.BUFF;
        this.priority = 99;
        //this.img = new Texture("img/powers/flightmod.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/Flyfan_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/Flyfan_128.png"),0,0,128,128);
        this.particleTimer = 0.0F;
        //this.particleTimer2 = 0.0F;
        ReimuMod.FlyCounter += 1;
    }

    /*
    public void stackPower(int stackAmount) {
        if (this.owner.hasPower(POWER)){
            if (!this.owner.hasPower(this.ID) && this.amount > this.owner.getPower(POWER).amount ) {
                this.owner.getPower(POWER).amount = this.amount;
            } else if (this.owner.hasPower(this.ID) && this.owner.getPower(POWER).amount < this.amount + this.owner.getPower(this.ID).amount ) {
                this.owner.getPower(POWER).amount = this.amount + this.owner.getPower(this.ID).amount;
            }
            this.owner.getPower(POWER).updateDescription();
        }
    }

     */

    public void updateParticles() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.04F;
                AbstractDungeon.effectsQueue.add(new FlyEffect());
            }
        }
    }

    //音效
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F);
    }

    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + unmber() + DESCRIPTIONS[2];
    }
    private  int unmber(){
        if(this.owner.isPlayer && AbstractDungeon.player.hasRelic("TurtleShell:ReiMu")){
            return s2;
        }else{
            return s1;
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return this.calculateDamageTakenAmount(damage, type);
    }


    private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type) {
        float x= (100-unmber()) / 100.F;
        return (this.amount>0 && type == DamageInfo.DamageType.NORMAL) ? damage * x : damage;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractPlayer p = AbstractDungeon.player ;
        ReimuMod.logger.info("#进入飞行挨揍判断:=================");
        ReimuMod.logger.info("飞行层数:"+this.amount+"|伤害:"+info);
        int moDamage = 1;
        if (info.owner != null && !info.owner.isPlayer ){
            AbstractMonster mo = (AbstractMonster) info.owner;
            //mo.createIntent();
            if (mo.intent == AbstractMonster.Intent.ATTACK || mo.intent == AbstractMonster.Intent.ATTACK_DEFEND || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF || mo.intent == AbstractMonster.Intent.ATTACK_BUFF) {
                moDamage = (Integer) ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentDmg");}
                ReimuMod.logger.info("m:"+mo+";Damage:"+moDamage);
        }
        if (!this.isDone && moDamage > 0 && !(this.owner.hasPower( "DreamingFengPower:ReiMu")) && this.owner != info.owner &&
                !(this.owner.hasPower("ShadingPower:ReiMu") && this.owner.getPower("ShadingPower:ReiMu").amount < 0) &&
                !this.owner.hasPower("ShadowOfCrowPower:ReiMu") && this.amount > 0 && info.owner != null && info.type == DamageInfo.DamageType.NORMAL) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction( p, p,"Flyfan:ReiMu", 1));
            //AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));  //不挡多段的飞行
        }else {
            ReimuMod.logger.info(info+"不满足飞行的触发条件！");
        }
        return damageAmount;
    }
@Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        less();
    }

    private void less(){
        this.flash();
        ReimuMod.logger.info("飞行Power:触发失去！");
        // 迫降判定
        /*
        if (this.owner.hasPower("ForcedLandingPower")) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.owner.getPower("ForcedLandingPower").amount, false), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
        }
         */
        if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("Bow_fan:ReiMu")) {
            ReimuMod.logger.info("遗物:蝴蝶结生效");
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, AbstractDungeon.player.getRelic("Bow_fan:ReiMu")));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(Bow_fan.s1, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
        }
        //常置
        if (this.owner.hasPower("InherentArrayPower:ReiMu")) {
            new setKami(this.owner.getPower("InherentArrayPower:ReiMu").amount, "lei");
        }
    }

    public void onRemove() {
        less();
        AbstractPlayer p = AbstractDungeon.player ;
        ReimuMod.logger.info("飞行耗尽！层数:"+this.amount);
        this.isDone = true;
        for (AbstractCard c : p.drawPile.group) {
            if (c instanceof Amulet) {
                ReimuMod.logger.info("抽牌堆找到护符啦！");
                AbstractDungeon.actionManager.addToTop(new decktohandpatch(c));
            }
        }
        for (AbstractCard c : p.discardPile.group) {
            if (c instanceof Amulet) {
                ReimuMod.logger.info("弃牌堆找到护符啦！");
                AbstractDungeon.actionManager.addToTop(new disToHandAction(c));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        if (p.hasRelic("SkyOrb:ReiMu")&&p.getRelic("SkyOrb:ReiMu").counter>0){
            p.getRelic("SkyOrb:ReiMu").counter = -1;
            p.getRelic("SkyOrb:ReiMu").flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new Flyfan(p, SkyOrb.s1),SkyOrb.s1));
        }
    }
}
