package ReimuMod.potions;

import ReimuMod.ReimuMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HakureiPotion extends AbstractPotion {
    public static final String ID = "HakureiPotion:ReiMu";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public HakureiPotion() {
        super(
                potionStrings.NAME,
                ID,
                PotionRarity.RARE,
                PotionSize.SPHERE,
                PotionColor.ENERGY);
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.getPotency() + potionStrings.DESCRIPTIONS[1];
        this.isThrown = false;
        this.labOutlineColor = ReimuMod.ReimuLIGHT;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractPower po : p.powers){
            if (po.type == AbstractPower.PowerType.BUFF){
                ReimuMod.logger.info("博丽秘药:决定为玩家施加" +po.name+":"+po);
                po.stackPower(this.getPotency());
            }
        }
        /*
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters){
            if (m!=null){
                for (AbstractPower po : m.powers){
                    if (po.type == AbstractPower.PowerType.BUFF){
                        ReimuMod.logger.info("博丽秘药:决定为"+m+"施加" +po.name+":"+po);
                        po.stackPower(this.potency);
                    }
                }
            }
        }
         */
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new HakureiPotion();
    }
}
