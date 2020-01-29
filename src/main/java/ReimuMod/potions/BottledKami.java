package ReimuMod.potions;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;

public class BottledKami extends AbstractPotion {
    public static final String ID = "BottledKami:ReiMu";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public BottledKami() {
        super(
                potionStrings.NAME,
                ID,
                PotionRarity.UNCOMMON,
                //PotionSize.EYE,
                PotionSize.EYE, PotionEffect.RAINBOW, new Color(225754111),  (Color)null, (Color)null

        );
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0]+this.potency+potionStrings.DESCRIPTIONS[1];
        this.isThrown = false;
        this.labOutlineColor = ReimuMod.ReimuLIGHT;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        ArrayList<String> list1 = new ArrayList<>();
        for (String n : setKami.kami) {
            if (setKami.getKami2.getKami2(n) < setKami.max()) { list1.add(n); }
        }
        int x = Math.min(list1.size(),this.getPotency());
        for (int i = x ;i>0;i--){
            int o = AbstractDungeon.potionRng.random(list1.size()-1);
            String k = list1.get (o);
            int a = setKami.max() - setKami.getKami2.getKami2(k);
            if (a > 0) {
                ReimuMod.logger.info("瓶装神明:决定降临" + k + "; 预计获取层数:" + a);
                new setKami(a, k);
            }
            list1.remove(o);
        }
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new BottledKami();
    }
}
