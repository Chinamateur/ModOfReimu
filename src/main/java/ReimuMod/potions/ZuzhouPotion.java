package ReimuMod.potions;

import ReimuMod.ReimuMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ZuzhouPotion extends AbstractPotion {
    public static final String ID = "ZuzhouPotion:ReiMu";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public ZuzhouPotion() {
        super(
                potionStrings.NAME,
                ID,
                PotionRarity.COMMON,
                PotionSize.M,
                PotionColor.ANCIENT);
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0]+this.potency+potionStrings.DESCRIPTIONS[1];
        this.isThrown = true;
        this.targetRequired = true;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        if (target!=null){
            for (AbstractPower po : target.powers){
                if (po.type == AbstractPower.PowerType.DEBUFF){
                    if (po.ID.equals("Strength")||po.ID.equals("Shackled")||po.amount<0){
                        ReimuMod.logger.info("诅咒:决定为"+target+"施加" +po.name+":"+po);
                        po.stackPower(-this.getPotency());
                    }else {
                        ReimuMod.logger.info("诅咒:决定为"+target+"施加" +po.name+":"+po);
                        po.stackPower(this.getPotency());
                    }
                }
            }
        }
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new ZuzhouPotion();
    }
}
