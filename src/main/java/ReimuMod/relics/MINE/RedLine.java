package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RedLine extends CustomRelic {
    public static final String NAME = "RedLine";
    public static final int s1 = 3;
    public static final int s2 = 1;
    public RedLine() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.BOSS,
                LandingSound.MAGICAL
        );
        this.counter = s1 ;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + s1 + this.DESCRIPTIONS[1] + s2 + this.DESCRIPTIONS[2];
    }
    public void atBattleStart() {
        this.counter = s1;
    }
    public void atTurnStart() {
        if (this.counter >0) {
            this.flash();
            if (this.counter != 1) {
                this.counter--;
            }else {
                this.counter = -1;
            }
            AbstractDungeon.actionManager.addToTop(new BetterDrawPileToHandAction(s2));
        }
    }
    public void justEnteredRoom(AbstractRoom room){
        this.counter = s1;
    }
    public AbstractRelic makeCopy() { return new RedLine(); }
}
