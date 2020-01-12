package ReimuMod.action.MINE.X;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SpiritualOutbreakAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int upgraded;
    private AbstractPlayer p;
    private int energyOnUse;
    private AbstractCard C;
    private AbstractMonster tar;
    public SpiritualOutbreakAction(AbstractCard c, int energyOnUse, AbstractMonster tar) {
        this.p = AbstractDungeon.player;
        this.upgraded = c.damage;
        this.freeToPlayOnce = c.freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.C = c ;
        this.tar = tar;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }


        if (effect > 0) {

            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(
                            this.tar,
                            new DamageInfo(p, this.upgraded*effect, this.C.damageTypeForTurn),
                            AbstractGameAction.AttackEffect.FIRE
                    )
            );
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction( this.tar,p,new WeakPower( this.tar,effect,false),effect));
            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
            /*
                Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                AbstractMonster mo;
                while(var3.hasNext()) {
                    mo = (AbstractMonster)var3.next();
                    this.addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber*cnt), -this.magicNumber*cnt, true, AbstractGameAction.AttackEffect.NONE));
                }
                var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(var3.hasNext()) {
                    mo = (AbstractMonster)var3.next();
                    if (!mo.hasPower("Artifact")) {
                        this.addToBot(new ApplyPowerAction(mo, p, new GainStrengthPower(mo, this.magicNumber*cnt), this.magicNumber*cnt, true, AbstractGameAction.AttackEffect.NONE));
                    }
                }
             */
        }

        this.isDone = true;
    }
}
