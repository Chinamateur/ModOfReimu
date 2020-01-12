package ReimuMod.action.MINE;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Iterator;

public class myMagicaction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private int x ;
    private boolean up;
    private boolean iso = false;
    private int magic ;
    private int Damage ;
    public myMagicaction (AbstractCard c) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.magic = c.magicNumber;
        this.up = c.upgraded ;
        this.Damage = c.damage;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                //this.isDone = true;
                iso = true;
                this.tickDuration();
            } else if (this.p.hand.size() <= this.magic) {
                while (!p.hand.isEmpty()){
                    if (this.p.hand.getBottomCard().costForTurn == -1) {
                        x += EnergyPanel.getCurrentEnergy();
                    } else if (this.p.hand.getBottomCard().costForTurn > 0) {
                        x += this.p.hand.getBottomCard().costForTurn;
                    }
                    this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                    this.iso = true;
                }
                this.tickDuration();
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.magic, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                iso = true;
                AbstractCard c;
                for(Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); this.p.hand.moveToExhaustPile(c)) {
                    c = (AbstractCard)var1.next();
                    if (c.costForTurn == -1) {
                        x += EnergyPanel.getCurrentEnergy();
                    } else if (c.costForTurn > 0) {
                        x += c.costForTurn;
                    }
                }
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            }
            this.tickDuration();
        }
        if ( iso) {
            if(x>0){
                if (this.up){
                    x *= 2;
                }
                new setKami(x,"yan");
            }
            if (p.hasPower("KamiYanPower:ReiMu")){
                x += p.getPower("KamiYanPower:ReiMu").amount;
            }
            x += this.Damage ;
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player,DamageInfo.createDamageMatrix(x, false), DamageInfo.DamageType.NORMAL, AttackEffect.FIRE));
            this.isDone = true;
        }
        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
        TEXT = uiStrings.TEXT;
    }
}
