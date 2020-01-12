package ReimuMod.relics.MINE;

import ReimuMod.powers.Flyfan;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class TurtleShell extends CustomRelic {
    public static final String NAME = "TurtleShell";
    public static final int s1 = 75;
    public static final int s2 = Flyfan.s1;
    public TurtleShell() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.RARE,
                LandingSound.SOLID
        );
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + s2 + this.DESCRIPTIONS[1] + s1 +this.DESCRIPTIONS[2];
    }

    public AbstractRelic makeCopy() {
        return new TurtleShell();
    }
}
