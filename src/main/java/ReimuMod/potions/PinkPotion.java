package ReimuMod.potions;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.MyChoseAction;
import ReimuMod.action.MINE.setKami;
import ReimuMod.cards.Defend_Lin;
import ReimuMod.cards.Strike_Lin;
import ReimuMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PinkPotion extends AbstractPotion {
    public static final String ID = "PinkPotion:ReiMu";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    public PinkPotion() {
        super(potionStrings.NAME, "PinkPotion", PotionRarity.COMMON, PotionSize.CARD, PotionColor.WEAK);
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0]+3*this.potency+potionStrings.DESCRIPTIONS[1]+this.potency+potionStrings.DESCRIPTIONS[2];
        this.isThrown = false;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new MyChoseAction(this.getPotency(),ReimuList(),false));
    }

    private CardGroup ReimuList () {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for(AbstractCard C : ReimuMod.cardsToAdd){
            if (C.color == AbstractCardEnum.REIMU_COLOR && !(C instanceof Strike_Lin ) && !(C instanceof Defend_Lin )){
                retVal.group.add(C);
            }
        }

        CardGroup derp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        while(derp.size() != 3*this.getPotency()) {
            boolean dupe = false;
            AbstractCard tmp = retVal.group.get(AbstractDungeon.potionRng.random(retVal.size()-1)).makeCopy();
            for (AbstractCard c : derp.group) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                tmp.costForTurn = 0 ;
                derp.addToBottom(tmp);
            }
        }
        return derp;
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new PinkPotion();
    }
}
