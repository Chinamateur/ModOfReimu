package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.PrepareAction;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import  ReimuMod.action.MINE.SkillandPowerfromdecktohand;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PrepareRite extends CustomCard {
    public static final String ID = "PrepareRite";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/PrepareRite.png";
    private static final int COST = 1;

    public PrepareRite() {
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
        this.exhaust = true;
        this.baseMagicNumber =this.magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = p.drawPile.size();
        x += p.discardPile.size();
        if (x>0){
            AbstractDungeon.actionManager.addToBottom(new PrepareAction(this));
        }

        //AbstractDungeon.actionManager.addToBottom(new SkillandPowerfromdecktohand(1));
        new setKami(this.magicNumber,"po");
        new setKami(this.magicNumber,"yue");
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.rawDescription = DESCRIPTION_UPG;
            //this.isInnate = true;
            this.upgradeMagicNumber(2);
            initializeDescription();
        }
    }

}
