package ReimuMod.potions;

import ReimuMod.ReimuMod;
import ReimuMod.powers.MagicPotionPower;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MagicPotion extends AbstractPotion {
    public static final String ID = "MagicPotion:ReiMu";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public MagicPotion() {
        super(
                potionStrings.NAME,
                ID,
                PotionRarity.RARE,
                PotionSize.BOTTLE,
                PotionColor.BLUE);
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0]+this.getPotency()+potionStrings.DESCRIPTIONS[1];
        this.isThrown = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new MagicPotionPower(p,this.getPotency()),this.getPotency()));
    }
    @Override
    public boolean canUse() {
        boolean x = super.canUse();
        if (!x) return x;
        AbstractPlayer p = AbstractDungeon.player;
        if (EnergyPanel.totalCount>=p.energy.energyMaster){
            return false;
        }
        return x;
    }
    public int getPotency(int ascensionLevel) {
        return 3;
    }

    public AbstractPotion makeCopy() {
        return new MagicPotion();
    }
}
