package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class PureWhite extends CustomRelic {
    public static final String NAME = "PureWhite";
    public static final int s1 = 1;
    public PureWhite() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.RARE,
                LandingSound.FLAT
        );
    }

    public String getUpdatedDescription() {
        return usedUp? this.DESCRIPTIONS[2] : this.DESCRIPTIONS[0] + s1 + this.DESCRIPTIONS[1];
    }
    public void atBattleStart() {
        usedUp = false;
        this.description = getUpdatedDescription();
    }
    public void onLoseHp(int damageAmount) {
        if(!usedUp){
            this.flash();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.usedUp = true;
            this.description = getUpdatedDescription();
        }
    }
    public void atTurnStart() {
        if (!usedUp) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, s1));
        }
    }
    public void justEnteredRoom(AbstractRoom room){
        if(this.usedUp){this.usedUp = false;this.description = getUpdatedDescription();}
    }
    public AbstractRelic makeCopy() { return new PureWhite(); }
}
