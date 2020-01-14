package ReimuMod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EnegyL extends AbstractPower {

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("EnegyL:ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EnegyL(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "EnegyL:ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/Reimupowers/energyNext.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/energyNext_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/energyNext_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription(){
        if (this.amount == 1){ this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }
        else { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2]; }
    }

    @Override
    public void onEnergyRecharge() {
        this.flash();
        AbstractDungeon.player.gainEnergy(this.amount);
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "EnegyL:ReiMu"));
    }

}
