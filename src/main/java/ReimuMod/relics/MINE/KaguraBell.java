package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class KaguraBell extends CustomRelic {
    public static final int s1 = 1;
    public AbstractRelic makeCopy() { return new KaguraBell(); }
    public static final String NAME = "KaguraBell";
    public KaguraBell() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.RARE,
                LandingSound.SOLID
        );
    }

    public String getUpdatedDescription() {
        return (DESCRIPTIONS[0]+s1+DESCRIPTIONS[1]);
    }
}
