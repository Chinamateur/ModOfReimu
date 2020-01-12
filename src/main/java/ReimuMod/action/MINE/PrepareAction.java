package ReimuMod.action.MINE;

import ReimuMod.ReimuMod;
import ReimuMod.characters.ReiMu;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.Iterator;
import java.util.UUID;

public class PrepareAction extends AbstractGameAction {
    private static final PowerStrings TEXT = CardCrawlGame.languagePack.getPowerStrings("TEXT:ReiMu");
    private CardGroup List2 = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private AbstractCard C;
    private AbstractPlayer p;
    public PrepareAction(AbstractCard cc) {
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.setValues(this.p, p, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.C = cc;
        for (AbstractCard c : p.discardPile.group){
            this.List2.addToBottom(c.makeSameInstanceOf());
        }
        for (AbstractCard c : p.drawPile.group){
            this.List2.addToBottom(c.makeSameInstanceOf());
        }
        if(this.List2.size()>0){
            for (AbstractCard c : this.List2.group){
                if (c.uuid == cc.uuid){
                    this.List2.removeCard(c);
                    break;
                }
            }
        }
    }



    public void update() {
        if (AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(this.List2 ,1, TEXT.NAME, false, false, false, false);
            this.tickDuration();
        } else {
            for(AbstractCard derp : AbstractDungeon.gridSelectScreen.selectedCards) {
                ReimuMod.logger.info("准备仪式:寻找"+derp);
                for (AbstractCard c : p.drawPile.group){
                    if (c.uuid == derp.uuid){
                        ReimuMod.logger.info("准备仪式:抽牌堆找到");
                        p.drawPile.removeCard(c);
                        p.drawPile.moveToDeck(c, false);
                        break;
                    }
                }
                for (AbstractCard c : p.discardPile.group){
                    if (c.uuid == derp.uuid){
                        ReimuMod.logger.info("准备仪式:弃牌堆找到");
                        p.discardPile.removeCard(c);
                        p.discardPile.moveToDeck(c, false);
                        break;
                    }
                }
                derp.unhover();
            }
            p.hand.refreshHandLayout();
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
            this.tickDuration();
        }
        this.tickDuration();
    }
}

