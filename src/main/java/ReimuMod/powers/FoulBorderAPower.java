package ReimuMod.powers;

import ReimuMod.ReimuMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;

public class FoulBorderAPower extends AbstractPower {

    public static final String NAME = "FoulBorderAPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FoulBorderAPower(AbstractCreature owner,int am) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = am;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/Reimupowers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (source == this.owner && target != this.owner && power.type == PowerType.DEBUFF ){
            int x = AbstractDungeon.cardRandomRng.random(4);
                switch (x){
                    case 0:{
                        ReimuMod.logger.info("犯规结界:准备施加虚弱");
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,target,new WeakPower(target,this.amount,this.owner.isPlayer),this.amount));
                        break;
                    }
                    case 1:{
                        ReimuMod.logger.info("犯规结界:准备施加易伤");
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,target,new VulnerablePower(target,this.amount,this.owner.isPlayer),this.amount));
                        break;
                    }
                    case 2:{
                        ReimuMod.logger.info("犯规结界:准备施加缠绕");
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,target,new ConstrictedPower(target,this.owner,this.amount),this.amount));
                        break;
                    }
                    case 3:{
                        ReimuMod.logger.info("犯规结界:准备减少力量");
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,target,new StrengthPower(target,-this.amount),-this.amount));
                        break;
                    }
                    case 4:{
                        ReimuMod.logger.info("犯规结界:准备施加中毒");
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,target,new PoisonPower(target,this.owner,this.amount),this.amount));
                        break;
                    }
                }

        }
    }
/*
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        card.damageTypeForTurn = dtype;
        ReimuMod.logger.info("犯规结界onPlayCard:"+card+"的伤害类型变为"+dtype);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        ReimuMod.logger.info("犯规结界onUseCard");
    }
 */

}
