package ReimuMod.powers;

import ReimuMod.ReimuMod;
import ReimuMod.cards.Sign;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MikOfLuckyPower extends AbstractPower {

    public static final String NAME = "MikOfLuckyPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MikOfLuckyPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/powers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        if (this.owner.isPlayer && this.amount>0){
            for(int i = this.amount; i>0;i--){
                this.flash();
                int s1 = AbstractDungeon.cardRng.random(3)+1;
                int s2 = AbstractDungeon.cardRng.random(2)+1;
                ReimuMod.logger.info("强运巫女生成: use:"+s1+"level"+s2);
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Sign(s1,s2), 1));
            }
        }
    }
}
