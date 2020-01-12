package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CheatingSign extends CustomRelic {
    public AbstractRelic makeCopy() { return new CheatingSign(); }
    public static final String NAME = "CheatingSign";
    public CheatingSign() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.SHOP,
                LandingSound.SOLID
        );
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
