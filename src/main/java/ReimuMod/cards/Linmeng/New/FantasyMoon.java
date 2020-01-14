package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.MyChoseAction;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class FantasyMoon extends CustomCard {
    public static final String ID = "FantasyMoon";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 1 ;

    public FantasyMoon() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        //this.baseDamage = 6;
        this.baseMagicNumber = this.magicNumber = 2;
        this.exhaust = true ;
    }
    public AbstractCard makeCopy() { return new FantasyMoon(); }


    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup derp = generateCardChoices();
        AbstractDungeon.actionManager.addToBottom(new MyChoseAction(1,derp,this.upgraded));
        new setKami(this.magicNumber,"roll" );
    }

    private CardGroup generateCardChoices() {
        CardGroup derp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        while(derp.size() != 3) {
            boolean dupe = false;
            AbstractCard tmp ;
            tmp = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();

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

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeMagicNumber(1);
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION ;
            initializeDescription();
        }
    }
}
