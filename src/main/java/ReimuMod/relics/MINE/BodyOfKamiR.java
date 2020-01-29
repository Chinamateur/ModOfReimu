package ReimuMod.relics.MINE;

import ReimuMod.cards.Linmeng.New.BodyOfKami;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BodyOfKamiR extends CustomRelic {
    private boolean cardSelected = true;
    public AbstractRelic makeCopy() { return new BodyOfKamiR(); }
    private static final PowerStrings TEXT = CardCrawlGame.languagePack.getPowerStrings("TEXT:ReiMu");
    public static final String NAME = "BodyOfKamiR";

    public BodyOfKamiR() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/Reimurelics/"+NAME+".png"),
                ImageMaster.loadImage("img/Reimurelics/outline/"+NAME+".png"),
                RelicTier.UNCOMMON,
                LandingSound.SOLID
        );
    }

    public void onEquip() {
        this.cardSelected = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
        CardGroup stanceChoices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for(int i = 0 ;i<7;i++ ){
            stanceChoices.addToBottom(new BodyOfKami(i));
        }
        //this.addToBot(new ChooseOneAction(stanceChoices.group));
        AbstractDungeon.cardRewardScreen.chooseOneOpen(stanceChoices.group);
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        //AbstractDungeon.gridSelectScreen.open(stanceChoices, 1,TEXT.NAME, false,false);
    }
/*
    public void update() {
        super.update();
        if (!this.cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            this.cardSelected = true;
            AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeStatEquivalentCopy();
            this.counter = c.baseHeal;
            //c.inBottleFlame = false;
            //c.inBottleLightning = false;
            //c.inBottleTornado = false;
           // AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }
    */
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new BodyOfKami(-this.counter)));
        //AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new BodyOfKami(this.counter), 1, true, true));
    }
/*
    public void onEquip() {
        this.cardSelected = false;
    }

    public void update() {
        super.update();
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() != 1 ){
            this.stanceChoices.clear();
            for (int z =0 ;z<7;z++){
                this.stanceChoices.addToBottom(new BodyOfKami(z).makeCopy());
            }
            AbstractDungeon.gridSelectScreen.open(this.stanceChoices, 1, this.DESCRIPTIONS[1], false, false, false, false);
        }
        if (!this.cardSelected && AbstractDungeon.gridSelectScreen.selectedCards.size() == 1) {
            this.cardSelected = true;
            AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeStatEquivalentCopy();
            c.inBottleFlame = false;
            c.inBottleLightning = false;
            c.inBottleTornado = false;
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
    }

     */

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
