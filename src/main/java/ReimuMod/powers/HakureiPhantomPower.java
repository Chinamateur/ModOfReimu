package ReimuMod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HakureiPhantomPower extends AbstractPower {
    public static final String NAME = "HakureiPhantomPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public HakureiPhantomPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.DEBUFF;
        //this.img = new Texture("img/powers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription() {
        if (this.amount>0){
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }else{
            this.description = DESCRIPTIONS[0];
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount >0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner,this.owner,new HakureiPhantomPower(this.owner,damageAmount),damageAmount));
        }
        return damageAmount;
    }
    @Override
    public void stackPower(int stackAmount) {
        if(stackAmount>0){
            if (this.amount<=0){
                this.amount = stackAmount ;
            }else {
                this.amount += stackAmount ;
            }
        }
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer){
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner,this.ID));
            if(this.amount>0){
                this.flash();
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(
                                this.owner,
                                new DamageInfo(AbstractDungeon.player, this.amount, DamageInfo.DamageType.THORNS),
                                AbstractGameAction.AttackEffect.FIRE
                        )
                );
            }
        }
    }
}
