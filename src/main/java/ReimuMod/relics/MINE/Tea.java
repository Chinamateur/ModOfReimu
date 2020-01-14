package ReimuMod.relics.MINE;

import ReimuMod.ReimuMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;

public class Tea extends CustomRelic {
    public static final String NAME = "Tea";
    public static final int s1 = 50;
    private static final int s2 = 30;
    public Tea() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/Reimurelics/"+NAME+".png"),
                ImageMaster.loadImage("img/Reimurelics/outline/"+NAME+".png"),
                RelicTier.UNCOMMON,
                LandingSound.CLINK
        );
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + s1 + this.DESCRIPTIONS[1] + s2 + this.DESCRIPTIONS[2];
    }

    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof RestRoom && AbstractDungeon.player.gold<s1) {
            this.flash();
            ReimuMod.logger.info("遗物:粗茶生效");
            int amountToGain = (int) (AbstractDungeon.player.maxHealth *(s2/100.F));
            AbstractDungeon.player.heal(amountToGain);
        }
    }
    public AbstractRelic makeCopy() { return new Tea(); }

}
