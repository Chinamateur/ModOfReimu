package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class DonationBox extends CustomRelic {
    public static final int s1 = 3;
    public AbstractRelic makeCopy() { return new DonationBox(); }
    public static final String NAME = "DonationBox";
    public DonationBox() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.BOSS,
                LandingSound.SOLID
        );
    }
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
    public String getUpdatedDescription() {
        return (DESCRIPTIONS[0]+s1+DESCRIPTIONS[1]);
    }
}
