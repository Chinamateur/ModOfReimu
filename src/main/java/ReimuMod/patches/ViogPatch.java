package ReimuMod.patches;

import ReimuMod.cards.Linmeng.New.SpiritualOutbreak2;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
public class ViogPatch {
    @SpirePatch(clz = VigorPower.class, method = "onUseCard")
    public static class Viog2Patch {
        @SuppressWarnings("rawtypes")
        @SpirePrefixPatch
        public static SpireReturn Prefix(AbstractPower _inst, AbstractCard card, UseCardAction action) {
            if (card instanceof SpiritualOutbreak2){
                return SpireReturn.Return(null);
            }else {
                return SpireReturn.Continue();
            }
        }
    }
}
