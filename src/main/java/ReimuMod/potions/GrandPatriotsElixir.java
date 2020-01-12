package ReimuMod.potions;

import ReimuMod.powers.Flyfan;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class GrandPatriotsElixir extends AbstractPotion {
    public static final String ID = "GrandPatriotsElixir:ReiMu";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public GrandPatriotsElixir() {
        super(
                potionStrings.NAME,
                ID,
                PotionRarity.UNCOMMON,
                PotionSize.T,
                PotionColor.SKILL
        );
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0]+this.getPotency()+potionStrings.DESCRIPTIONS[1];
        this.isThrown = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p,p,new VigorPower(p,this.getPotency()),this.getPotency()));
    }

    public int getPotency(int ascensionLevel) {
        return 13;
    }

    public AbstractPotion makeCopy() {
        return new GrandPatriotsElixir();
    }
}
