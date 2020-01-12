package ReimuMod.relics.MINE;

import ReimuMod.powers.Flyfan;
import ReimuMod.powers.SealPower;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.CuriosityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FadedRune extends CustomRelic {
    public static final String NAME = "FadedRune";
    public FadedRune() {
        super(
                "FadedRune:ReiMu",
                ImageMaster.loadImage("img/relics/FadedRune.png"),
                ImageMaster.loadImage("img/relics/outline/FadedRune.png"),
                RelicTier.UNCOMMON,
                LandingSound.FLAT
        );
    }

    public void  atBattleStart() {
        this.flash();
        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster) null, true, AbstractDungeon.relicRng);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,AbstractDungeon.player,new SealPower(m),0));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() { return new FadedRune(); }
}

