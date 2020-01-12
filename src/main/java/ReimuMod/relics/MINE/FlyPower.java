package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ReimuMod.powers.Flyfan;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

public class FlyPower extends CustomRelic {
    public FlyPower() {
        super(
                "FlyPower:ReiMu",
                ImageMaster.loadImage("img/relics/yinyangyu.png"),
                ImageMaster.loadImage("img/relics/outline/yinyangyu.png"),
                RelicTier.STARTER,
                LandingSound.FLAT
        );
    }

    public void  atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, FlyPower.this));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
                (AbstractDungeon.player, AbstractDungeon.player,
                        new Flyfan(AbstractDungeon.player, 1), 1
                )
        );
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() { return new FlyPower(); }

}
