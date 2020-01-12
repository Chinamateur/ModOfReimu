package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.X.CardOfSanctityAction;
import ReimuMod.action.MINE.setKami;
import ReimuMod.cards.Sign;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import basemod.devcommands.energy.Energy;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.DoppelgangerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class CardOfSanctity extends CustomCard {
    public static final String ID = "CardOfSanctity";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = -1;

    public CardOfSanctity() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                AbstractCard.CardTarget.SELF
        );
        //this.exhaust = true;
    }
    public AbstractCard makeCopy() { return new CardOfSanctity(); }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new CardOfSanctityAction(this, this.energyOnUse));

        /*
        ReimuMod.logger.info("打出卡牌:"+ID);
        int x = 7 - p.hand.size();
        if (x > 0){
            for (int i= 0;i < x;i++){
                AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
                if (this.upgraded) { c.upgrade(); }
                ReimuMod.logger.info("天降宝牌: 尝试添加卡牌:"+c);
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
            }
            new setKami(x,"zhi");
        }
         */
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }

}
