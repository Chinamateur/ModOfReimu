package ReimuMod.powers.Kami;

import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Collections;

public class KyokaiOfKamiPower extends AbstractPower {
    private static final setKami.getKami2 j = new setKami.getKami2();
    public static final String NAME = "KyokaiOfKamiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public KyokaiOfKamiPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/powers/KyokaiOfKamiPower.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/KyokaiOfKamiPower_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/KyokaiOfKamiPower_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        // this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (this.amount/3) + DESCRIPTIONS[2];
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            if(this.amount>0){
                this.flash();
                this.amount -= 1 ;
                updateDescription();
                //AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            } else {
                this.flash();
                new setKami(-999,"zhi");
                new setKami(-999,"yang");
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner,this.ID));
            }
        }
    }
}
