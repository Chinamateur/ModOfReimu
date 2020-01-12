package ReimuMod.action.MINE;

import ReimuMod.cards.Sign;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class NBAction extends AbstractGameAction {
    private AbstractCreature m;
    private AbstractPlayer p;
    private int h;
    private AbstractCard card;
    public NBAction(AbstractCard c) {
        this.p = AbstractDungeon.player;
        this.m = null;
        this.card = c;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
    }
    public NBAction(AbstractCard c,AbstractCreature mm) {
        this.p = AbstractDungeon.player;
        this.card = c;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.m =  mm ;
    }

    public void update() {
        if(this.card.baseHeal == 0){
            for (int i = 2; i >0; i--) {
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.card.block)
                );
            }
            new setKami(this.card.magicNumber,"po");
            this.card.baseHeal = 1;
            this.card.flash(Color.SKY.cpy());
        }else {
            for (int i = 2; i >0; i--) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(
                                m,
                                new DamageInfo(p, this.card.damage, this.card.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.SLASH_DIAGONAL
                        )
                );
            }
            new setKami(this.card.magicNumber,"yue");
            this.card.baseHeal = 0;
            this.card.flash(Color.PINK.cpy());
        }
        this.card.applyPowers();
        this.isDone = true;
    }
}
