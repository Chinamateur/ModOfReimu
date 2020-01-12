package ReimuMod.powers.Kami;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.LightAction;
import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class OutlierPower extends AbstractPower {
    public static final int number = 20;
    public static final String NAME = "OutlierPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OutlierPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        //this.img = new Texture("img/powers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_128.png"),0,0,128,128);
    }
    /*
    @Override
    public void stackPower(int stackAmount) {
        //hha
    }

     */
    //描述文本
    @Override
    public void stackPower(int stackAmount) {
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + number + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        ReimuMod.logger.info("神罚触发");
        if(this.owner.hasPower(this.ID)&&this.owner.getPower(this.ID).amount==-1){
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new LightAction(new DamageInfo(this.owner, number, DamageInfo.DamageType.THORNS), (AbstractMonster) this.owner, false));
            //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner,this.owner,new WeakPower(this.owner,1,false),1));
            //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner,this.owner,new VulnerablePower(this.owner,1,false),1));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction (this.owner, this.owner, this.ID));
        }
    }
}
