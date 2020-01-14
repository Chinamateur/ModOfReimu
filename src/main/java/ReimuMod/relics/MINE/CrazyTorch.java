package ReimuMod.relics.MINE;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CrazyTorch extends CustomRelic {
    private static final AbstractCard c = new Madness().makeCopy();
    public AbstractRelic makeCopy() { return new CrazyTorch(); }
    public static final String NAME = "CrazyTorch";
    //public static final int Burn = 1;
    public static final int Madness = 1;
    public CrazyTorch() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/Reimurelics/"+NAME+".png"),
                ImageMaster.loadImage("img/Reimurelics/outline/"+NAME+".png"),
                RelicTier.UNCOMMON,
                LandingSound.MAGICAL
        );
        c.upgrade();
    }

    public void atBattleStartPreDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, Madness, false));
        /*
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard c;
        Iterator var3 = p.masterDeck.group.iterator();
        ArrayList<AbstractCard> A = new ArrayList<>();
        while (var3.hasNext()) {
            c = (AbstractCard) var3.next();
            if (c.costForTurn > 0 || c.cost > 0) {
                A.add(c);
            }
        }
        if (A.size() > 0) {
            c = A.get(AbstractDungeon.relicRng.random(A.size() - 1));
            if (c != null) {
                ReimuMod.logger.info("狂乱火把发动！~目标为:" + c);
                c.cost = 0;
                c.costForTurn = 0;
                c.isCostModified = true;
                c.applyPowers();
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            }
        }

         */
    }

/*

        //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Burn(), Burn, false));
        //this.grayscale = true;
        this.usedUp = true;
        this.description = MSG[2];
        this.initializeTips();

    public void justEnteredRoom(AbstractRoom room){
        //this.grayscale = false;
        this.usedUp = false;
        this.description = getUpdatedDescription();
        this.initializeTips();
    }
 */
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
