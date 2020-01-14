package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CrownOfHeavens extends CustomRelic {
    public static final int s1 = 20;
    public AbstractRelic makeCopy() { return new CrownOfHeavens(); }
    public static final String NAME = "CrownOfHeavens";
    public CrownOfHeavens() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/Reimurelics/"+NAME+".png"),
                ImageMaster.loadImage("img/Reimurelics/outline/"+NAME+".png"),
                RelicTier.BOSS,
                LandingSound.SOLID
        );
    }

    public String getUpdatedDescription() {
        return (DESCRIPTIONS[0]+s1+DESCRIPTIONS[1]);
    }
}
