package ReimuMod.cards;

import ReimuMod.ReimuMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class Sign extends CustomCard {

    public static final String ID = "Sign";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPDESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXDESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 0;
    private final int USE;
    private int LEVEL;
    public Sign() {
        this(-1,-1);
    }
    public Sign(int use ,int level) {
        super(
                ID+":ReiMu",
                (use == -1 )? NAME : EXDESCRIPTION[level],
                "img/Reimucards/"+ID+level+".png",
                COST,
                DESCRIPTION,
                CardType.SKILL,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.exhaust = true ;
        this.USE = use;
        this.LEVEL = level;
        if(use != -1){
            if (AbstractDungeon.player.hasPower("GatheringPower:ReiMu")){
                this.upgrade();
            }
            if(AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("CheatingSign:ReiMu") && level < 2 ){
                int x = AbstractDungeon.cardRandomRng.random(1)+2;
                ReimuMod.logger.info("遗物:作弊签筒生效; 原本:"+level+"变为:"+x);
                this.LEVEL = x;
                AbstractDungeon.player.getRelic("CheatingSign:ReiMu").flash();
                this.loadCardImage("img/Reimucards/"+ID+this.LEVEL+".png");
            }
            chushi();
        }
    }
    public void onRetained() {
        if(this.upgraded && this.LEVEL <3){
            ReimuMod.logger.info(this+"神签升级！ 原本:"+this.LEVEL);
            this.LEVEL ++ ;
            this.loadCardImage("img/Reimucards/"+ID+this.LEVEL+".png");
            chushi();
            this.flash();
        }
    }
    private void chushi(){
        this.name = (this.USE == -1 )? NAME : EXDESCRIPTION[this.USE +15];
        this.baseBlock = this.LEVEL*5;
        this.baseDamage = this.LEVEL*7;
        this.baseMagicNumber = this.magicNumber = this.LEVEL * 10;
        //卡名
        //this.NAME = EXDESCRIPTION[this.LEVEL];
        //描述
        this.rawDescription = EXDESCRIPTION[this.USE+4];
        //抽卡数
        if( this.USE == 2 ){
            this.type = CardType.ATTACK ;
            this.target = CardTarget.ENEMY;
            this.loadCardImage("img/Reimucards/Signa"+this.LEVEL+".png");
        }
        if (this.USE == 3 ){
            this.rawDescription += (this.LEVEL == 0 ? 0 : (this.LEVEL + 1) );
            this.rawDescription += EXDESCRIPTION[8];
        }
        if (this.USE == 4){
            if ( this.LEVEL >= 0) {
                this.rawDescription = EXDESCRIPTION[9];
                for (int i = 0; i < this.LEVEL; i++) {
                    this.rawDescription += "[E] ";
                }
                this.rawDescription += "。";
            }
        }
        //如果是金钱大凶
        if((this.USE == 4 || this.USE == 0) && this.LEVEL == 0){ this.rawDescription = EXDESCRIPTION[10]; }

        this.rawDescription += EXDESCRIPTION[11];

        ReimuMod.logger.info("USE:"+this.USE+"LEVEL:"+this.LEVEL);
        if(this.upgraded){
            this.rawDescription = UPDESCRIPTION + this.rawDescription;
            if (this.LEVEL!=3){
                this.rawDescription += EXDESCRIPTION[14];
            }
        }
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReimuMod.logger.info("神签:发挥效果:"+this.USE+";等级:"+this.LEVEL);
        switch (this.USE) {
            case 1: //格挡
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
                break;
            case 2: //伤害
                if (m == null){
                    ReimuMod.logger.info("神签:发挥效果:"+this.USE+";等级:"+this.LEVEL);
                    m = AbstractDungeon.getMonsters().getRandomMonster(true);
                }
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                break;
            case 3: //抽卡
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.LEVEL == 0 ? 0 : this.LEVEL+1 ));
                break;
            case 4: //能量
                if (this.LEVEL!=0){
                    AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.LEVEL));
                }
                break;
            default: //金钱
                ReimuMod.logger.info("获得金钱:"+this.magicNumber);
                p.gainGold(this.magicNumber);
                for(int i = this.magicNumber; i > 0;i--){
                    AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, true));
                }
                break;
        }
    }
    public AbstractCard makeCopy() { return new Sign(this.USE ,this.LEVEL); }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true ;
            if ( this.USE!= -1 ){
                chushi();
            }
        }
    }
}
