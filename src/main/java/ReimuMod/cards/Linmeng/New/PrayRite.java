package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.MyChoseAction;
import ReimuMod.cards.Sign;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PrayRite extends CustomCard {
    public static final String ID = "PrayRite";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;

    public PrayRite() {
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
        this.exhaust = true;
        //this.baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup stanceChoices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 1;i<5;i++){
            stanceChoices.addToBottom(new Sign(i,3));
        }
        AbstractDungeon.actionManager.addToBottom(new MyChoseAction(1,stanceChoices,false,false));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            //this.upgradeBlock(2);
            initializeDescription();
        }
    }
}
