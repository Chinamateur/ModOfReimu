package ReimuMod.relics.MINE;

import ReimuMod.ReimuMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class Mastiff extends CustomRelic {
    public static final String NAME = "Mastiff";
    public static final int s1 = 2;
    public Mastiff() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.COMMON,
                LandingSound.SOLID
        );
        this.counter = 2;
    }
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.type == AbstractCard.CardType.STATUS && this.counter > 0){
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            ReimuMod.logger.info("阿吽吃掉了"+drawnCard+drawnCard.uuid);
            AbstractDungeon.player.drawPile.moveToExhaustPile(drawnCard);
            AbstractDungeon.actionManager.addToTurnStart(new DrawCardAction(AbstractDungeon.player, 1));
            this.counter -- ;
            if (this.counter<=0){
                this.usedUp = true;
                this.description = this.DESCRIPTIONS[2];
            }
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
        }
    }
    public void justEnteredRoom(AbstractRoom room){
            //this.grayscale = false;
            if (this.counter!=2){
                this.usedUp = false;
                this.counter = 2 ;
                this.description = getUpdatedDescription();
                this.tips.clear();
                this.initializeTips();
            }
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + s1 + this.DESCRIPTIONS[1];
    }
    public void atBattleStartPreDraw() {
        if (this.counter!=2){
            this.counter = 2;
            this.tips.clear();
            this.initializeTips();
        }
    }
    public AbstractRelic makeCopy() {
        return new Mastiff();
    }
}
