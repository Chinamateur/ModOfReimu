package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Bow_fan extends CustomRelic {
    public static final int s1 = 4;
    public AbstractRelic makeCopy() { return new Bow_fan(); }
    public static final String NAME = "Bow_fan";
    public Bow_fan() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.COMMON,
                LandingSound.SOLID
        );
        //this.counter=s1;
    }
    public String getUpdatedDescription() {
        return (DESCRIPTIONS[0]+s1+DESCRIPTIONS[1]);
    }
}
