package ReimuMod.powers;

import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class DependentFormPower extends AbstractPower {

    public static final String NAME = "DependentFormPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DependentFormPower(AbstractCreature owner, int amount) {
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
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            String[] kami = {"炎","破","智","月","雷","风","阳"};
            ArrayList<String> list1 = new ArrayList<>();
            setKami.getKami2 x = new setKami.getKami2();
            for (String i : kami) {
                if (x.getKami2(i) < setKami.max()) {
                    list1.add(i);
                }
            }
            if (list1.size()>0){
                int z = AbstractDungeon.cardRng.random(list1.size()-1);
                this.flash();
                new setKami(this.amount, list1.get(z));
            }
        }
    }
}
