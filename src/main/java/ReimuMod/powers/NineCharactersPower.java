package ReimuMod.powers;

import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NineCharactersPower extends AbstractPower {
    private static final String[] kami = {"炎","破","智","月","雷","风","阳"};
    public static final String NAME = "NineCharactersPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NineCharactersPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/Reimupowers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+ DESCRIPTIONS[1];
    }

    //回合结束
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer && this.amount>0){
            int d = 0;
            setKami.getKami2 x = new setKami.getKami2();
            for (String a : kami){
                if (x.getKami2(a)>0){
                    d++;
                }
            }
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner,this.owner,d*this.amount));
        }
    }
}

