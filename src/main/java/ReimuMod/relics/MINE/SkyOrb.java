package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class SkyOrb extends CustomRelic {
    public static final int s1 = 1;
    private static final int s2 = 1;
    public AbstractRelic makeCopy() { return new SkyOrb(); }
    public static final String NAME = "SkyOrb";
    public SkyOrb() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/Reimurelics/SkyOrb.png"),
                ImageMaster.loadImage("img/Reimurelics/outline/SkyOrb.png"),
                RelicTier.COMMON,
                LandingSound.MAGICAL
        );
        this.counter = s2;
    }
    public void atBattleStartPreDraw() {
        this.counter = s2;
    }
    public void onVictory() {
        this.counter = s2;
    }
    public void justEnteredRoom(AbstractRoom room){
        this.counter = s2;
    }
    public String getUpdatedDescription() {
        return (DESCRIPTIONS[0]+s1+DESCRIPTIONS[1]);
    }
}
