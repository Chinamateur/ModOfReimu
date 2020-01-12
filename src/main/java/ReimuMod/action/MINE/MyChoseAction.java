package ReimuMod.action.MINE;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import java.util.Iterator;

public class MyChoseAction extends AbstractGameAction {
    private AbstractPlayer p;
    private static final PowerStrings TEXT = CardCrawlGame.languagePack.getPowerStrings("TEXT:ReiMu");
    private final boolean upgrade;
    private CardGroup List ;
    private int Number ;
    private boolean Any ;
    private static boolean tdo = true ;

    public MyChoseAction(int number,CardGroup list,boolean upgrade) {
        this.upgrade = upgrade;
        this.List = list;
        this.Number = number;
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.Any = true ;
        tdo = true ;
    }
    public MyChoseAction(int number,CardGroup list,boolean upgrade,boolean any) {
        this.upgrade = upgrade;
        this.List = list;
        this.Number = number;
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.Any = any ;
        tdo = true ;
    }


    public void update() {
        //if (this.duration == Settings.ACTION_DUR_FAST) {
            if (!tdo && this.Any && AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()){
                this.isDone = tdo = true ;
                this.tickDuration();
            }else if (AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                //CardGroup a = new CardGroup()
                if (this.upgrade){
                    for (AbstractCard a : this.List.group){
                        a.upgrade();
                    }
                }
                //AbstractDungeon.gridSelectScreen.open(this.List , this.Number,TEXT.NAME, false, false, this.Any, false);
                AbstractDungeon.gridSelectScreen.open(this.List , this.Number, TEXT.NAME, false, false, this.Any, false);
                //AbstractDungeon.gridSelectScreen.open(this.List , this.Number,false,TEXT.NAME);
                tdo = false ;
                this.tickDuration();
            }
            else {
                Iterator c;
                AbstractCard derp;
                for(c = AbstractDungeon.gridSelectScreen.selectedCards.iterator(); c.hasNext(); derp.unhover()) {
                    derp = (AbstractCard)c.next();
                    this.p.hand.addToHand(derp);
                    if (AbstractDungeon.player.hasPower("Corruption") && derp.type == AbstractCard.CardType.SKILL) {
                        derp.setCostForTurn(-9);
                    }
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
                this.isDone = true;
                this.tickDuration();
            }
            //AbstractDungeon.gridSelectScreen.anyNumber=false;
            this.tickDuration();
        //}
    }

}