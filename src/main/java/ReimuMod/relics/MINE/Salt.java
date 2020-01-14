package ReimuMod.relics.MINE;

import ReimuMod.ReimuMod;
import ReimuMod.powers.SaltPower;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Salt extends CustomRelic {
    public static final String NAME = "Salt";
    public static final int s1 = 4;
    public Salt() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/Reimurelics/"+NAME+".png"),
                ImageMaster.loadImage("img/Reimurelics/outline/"+NAME+".png"),
                RelicTier.RARE,
                AbstractRelic.LandingSound.CLINK
        );
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + s1 + this.DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        this.flash();
        ReimuMod.logger.info("遗物:驱魔盐生效");
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new SaltPower(AbstractDungeon.player)));
    }
    public AbstractRelic makeCopy() { return new Salt(); }
}
