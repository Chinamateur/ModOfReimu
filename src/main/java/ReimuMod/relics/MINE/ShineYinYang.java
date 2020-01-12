package ReimuMod.relics.MINE;

import ReimuMod.powers.Flyfan;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ShineYinYang extends CustomRelic {
    public static final int s1 = 3;
    public AbstractRelic makeCopy() { return new ShineYinYang(); }
    public static final String NAME = "ShineYinYang";
    public ShineYinYang() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.BOSS,
                LandingSound.SOLID
        );
    }
    public void obtain() {
        if (AbstractDungeon.player.hasRelic("FlyPower:ReiMu")) {
            instantObtain(AbstractDungeon.player, 0, false);
        } else {
            super.obtain();
        }
    }
    public String getUpdatedDescription() {
        return (DESCRIPTIONS[0]+s1+DESCRIPTIONS[1]);
    }
    public void  atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
                (AbstractDungeon.player, AbstractDungeon.player,
                        new Flyfan(AbstractDungeon.player, s1), s1
                )
        );
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("FlyPower:ReiMu");
    }
}
