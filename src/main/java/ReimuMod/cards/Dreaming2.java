package ReimuMod.cards;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.*;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.EnegyL;
import ReimuMod.powers.Kami.*;
import ReimuMod.powers.Kami.Effect.test;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import java.util.Iterator;

public class Dreaming2 extends CustomCard {
    public static final String ID = "Dreaming2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPDESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXDESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 2;
    public Dreaming2() {
        super(
                ID+":ReiMu",
                NAME,
                "img/Reimucards/Dreaming27.png",
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY
        );
        this.baseHeal = this.heal = 7;
        this.baseMagicNumber = this.magicNumber = 4;
        this.baseDamage = 4;
        this.baseBlock = 4 ;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        switch (this.baseHeal){
            case 0: {// 炎 伤害所有敌人
                if (p.hasPower(KamiYanPower.NAME+":ReiMu")&&p.getPower(KamiYanPower.NAME+":ReiMu").amount>=setKami.max()){
                    for (int i = this.magicNumber; i > 0; i--) {
                        p.getPower(KamiYanPower.NAME+":ReiMu").atStartOfTurn();
                        AbstractDungeon.actionManager.addToBottom(new WaitAction(1F));
                        //AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, true));
                    }
                }
                break;
            }
            case 1: {// 破 集中单伤
                AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, p));
                AbstractDungeon.effectsQueue.add(new test(m.hb.x,m.hb.y));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY)
                );
                break;
            }
            case 2: {//雷 虚弱与降力量              //伤害与负面
/*
                AbstractDungeon.actionManager.addToTop(new LightningOrbEvokeAction(new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL), true));
                for ( AbstractMonster mo : AbstractDungeon.getMonsters().monsters){
                    if(mo!=null && !mo.isDying && !mo.isDead &&!mo.halfDead){
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(mo,p,new OutlierPower(mo)));
                    }
                }
 */
                for (AbstractMonster m3 : AbstractDungeon.getMonsters().monsters) {
                    if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(m3, p, new WeakPower(m3, this.magicNumber, false), this.magicNumber));
                    }
                }
                for (AbstractMonster m3 : AbstractDungeon.getMonsters().monsters) {
                    if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                        this.addToBot(new ApplyPowerAction(m3, p, new StrengthPower(m3, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                    }
                }

                for (AbstractMonster m3 : AbstractDungeon.getMonsters().monsters) {
                    if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                        AbstractDungeon.actionManager.addToTop(new VFXAction(new LightningEffect(m3.drawX, m3.drawY), 0.1F));
                    }
                }

                break;
            }
            case 3: {//月
                for (int i = this.magicNumber;i>0;i--){
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new tenlighetEffect(p.hb.cX,p.hb.cY,m.hb.cX,m.hb.cY,setKami.Kami10.KamiColoer(3)),0.3F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                            new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true)
                    );
                }
                AbstractDungeon.actionManager.addToBottom(new ScryAction(this.magicNumber));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
                        (p, p, new EnegyL(p, 2), 2)
                );
                //AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
                if(p.drawPile.isEmpty()){
                    AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
                }
                break;
            }
            case 4: {//山)
                for (int i = this.magicNumber;i>0;i--){
                    AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                    ReimuMod.logger.info("梦想封印 山 随机目标"+mo);
                    if (mo!=null&&!mo.isDead&&!mo.isDying&&!mo.halfDead){
                        ReimuMod.logger.info("梦想封印 山 触发");
                        this.addToBot(new VFXAction(new WeightyImpactEffect(mo.hb.cX, mo.hb.cY,setKami.Kami10.KamiColoer(4))));
                        this.addToBot(new WaitAction(0.8F));
                        AbstractDungeon.actionManager.addToBottom(new AttackAndBlockAction(mo,new DamageInfo(p,this.damage,this.damageTypeForTurn)));
                    }
                }
                break;
            }

            case 5: {//风 无效并反伤       //减费
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DreamingFengPower(p,this.magicNumber),this.magicNumber));
                break;
            }
            case 6: {//阳 回血
                if (Settings.FAST_MODE) { this.addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
                } else { this.addToBot(new VFXAction(new GrandFinalEffect(), 1.0F)); }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DreamingYangPower(p,1,this.upgraded),1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new RegenPower(AbstractDungeon.player, this.magicNumber),this.magicNumber));
                break;
            }
            default: {
                for (int i = this.magicNumber; i > 0; i--) {
                    AbstractDungeon.actionManager.addToTop(new draeaming2A(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
                break;
            }
        }
        this.baseHeal = this.heal= 7;
        AbstractDungeon.actionManager.addToBottom(new ApplyPower2Action(this));
    }
    @Override
    public void applyPowers(){
        change();
        super.applyPowers();
    }
    public void triggerOnGlowCheck() {
        if(this.baseHeal!=7){
            this.glowColor = setKami.Kami10.KamiColoer(this.baseHeal).cpy();
        }else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    private void change(){
        switch (this.baseHeal){
            case 0: {// 炎   发动2(3)次           // 所有敌人流失 炎之信层数2倍的生命
                //this.baseDamage = 7 ;
                this.type =CardType.SKILL;
                this.baseMagicNumber = this.magicNumber = 3;
                if(this.upgraded){this.baseMagicNumber+=1;}
                this.magicNumber = this.baseMagicNumber;
                this.target = CardTarget.ALL_ENEMY;
                this.cardsToPreview = null ;
                break;
            }
            case 1: {// 破 集中单伤
                this.type =CardType.ATTACK;
                this.baseDamage = 46;
                if(this.upgraded){
                    this.baseDamage += 20;
                }
                this.target = CardTarget.ENEMY;
                this.baseMagicNumber = this.magicNumber = 1;
                this.cardsToPreview = null ;
                break;
            }
            case 2: {//雷
                this.type =CardType.SKILL;
                this.target = CardTarget.ALL_ENEMY;
                this.baseMagicNumber =  4;
                if(this.upgraded){
                    this.baseMagicNumber += 1;
                }
                this.magicNumber =this.baseMagicNumber;
                this.cardsToPreview = null ;
                break;
            }
            case 3: {//月
                this.type =CardType.ATTACK;
                this.target = CardTarget.ENEMY;
                this.baseDamage = 4;
                this.baseMagicNumber = 4;
                if(this.upgraded){
                    this.baseMagicNumber += 1;
                }
                this.magicNumber = this.baseMagicNumber;
                this.cardsToPreview = null ;
                break;
            }
            case 4: {//山
                this.type =CardType.ATTACK;
                this.baseDamage = 10;
                this.target = CardTarget.ALL_ENEMY;
                this.baseMagicNumber = 2;
                if(this.upgraded){
                    this.baseMagicNumber += 1;
                }
                this.magicNumber = this.baseMagicNumber;
                this.cardsToPreview = null ;
                break;
            }
            case 5: {//风 免伤并反击|减费
                this.type =CardType.SKILL;
                this.target = CardTarget.ENEMY;
                //this.baseDamage = 10;
                this.target = CardTarget.SELF;
                this.baseMagicNumber = 20 ;
                if(this.upgraded){
                    this.baseMagicNumber += 5;
                }
                this.magicNumber =this.baseMagicNumber;
                this.cardsToPreview = null ;
                //if (!this.freeToPlayOnce&&this.costForTurn > 1){this.costForTurn = 1;}
                break;
            }
            case 6: {//阳 随后冲一张 0 费
                this.type =CardType.POWER;
                this.baseMagicNumber = this.magicNumber = 5;
                //this.baseMagicNumber = this.magicNumber = 1;
                this.target = CardTarget.SELF;
                AbstractCard c = new Sakura();
                if (this.upgraded){
                    c.upgrade();
                }
                this.cardsToPreview = c;
                break;
            }
            default: {
                this.type =CardType.ATTACK;
                this.baseMagicNumber = 4;
                this.baseDamage = 4;
                if(this.upgraded){
                    this.baseMagicNumber += 1;
                }
                this.magicNumber = this.baseMagicNumber;
                this.target = CardTarget.ALL_ENEMY;
                this.cardsToPreview = null ;
                break;
            }
        }
        this.loadCardImage("img/Reimucards/"+ID+this.baseHeal+".png");
        this.name = EXDESCRIPTION[this.baseHeal];
        this.rawDescription = EXDESCRIPTION[this.baseHeal+8];
        if (this.heal == 6 && this.upgraded){
            this.rawDescription = EXDESCRIPTION[16];
        }
        this.initializeDescription();
    }
    /*
    public void atTurnStart() {
        this.retain=this.upgraded;
    }
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        this.baseHeal = this.heal= 7;
        this.retain=this.upgraded;
        this.applyPowers();
    }
     */
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            change();
            this.initializeDescription();
        }
    }
}
