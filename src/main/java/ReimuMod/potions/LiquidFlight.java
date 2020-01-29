package ReimuMod.potions;

import ReimuMod.ReimuMod;
import ReimuMod.powers.Flyfan;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class LiquidFlight extends AbstractPotion {
    public static final String ID = "LiquidFlight:ReiMu";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public LiquidFlight() {
        super(
                potionStrings.NAME,
                ID,
                PotionRarity.COMMON,
                PotionSize.BOLT,
                PotionColor.BLUE);
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0]+this.potency+potionStrings.DESCRIPTIONS[1];
        this.isThrown = false;
        this.labOutlineColor = ReimuMod.ReimuLIGHT;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new Flyfan(p,this.getPotency()),this.getPotency()));
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new LiquidFlight();
    }
}
