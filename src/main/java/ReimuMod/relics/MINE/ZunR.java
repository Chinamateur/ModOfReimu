package ReimuMod.relics.MINE;

import ReimuMod.cards.Linmeng.New.BodyOfKami;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ZunR extends CustomRelic {
    public static final int s1 = 1;
    public AbstractRelic makeCopy() { return new ZunR(); }
    public static final String NAME = "ZunR";
    public ZunR() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/Reimurelics/"+NAME+".png"),
                ImageMaster.loadImage("img/Reimurelics/outline/"+NAME+".png"),
                RelicTier.SPECIAL,
                LandingSound.CLINK
        );
    }
    public void atTurnStart() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Dazed(), s1));
    }
    /*
    public void atBattleStart() {
        int x = Math.min(s1,AbstractDungeon.player.drawPile.size());
        if (x>0){
            for (int p =x;p>0;p--){
                AbstractCard c = AbstractDungeon.player.drawPile.getRandomCard(AbstractDungeon.relicRng);
                AbstractDungeon.player.drawPile.removeCard(c);
            }
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(new Dazed(), x, true, true));
        }

    }

     */
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
