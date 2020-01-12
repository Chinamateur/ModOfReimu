package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.patches.decktohandpatch;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Amulet extends CustomCard{
    public static final String ID = "Amulet";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 0;

    public Amulet() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.COMMON,
                AbstractCard.CardTarget.SELF
        );
        this.baseBlock = 4;
        this.baseMagicNumber = this.magicNumber = 1;
        //this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block)
        );
        //new setKami(this.magicNumber,"yue");
        //AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        /*
        if (!Settings.FAST_MODE) {AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block)
        );}
         */
    }

    @Override
    public void tookDamage() {
        ReimuMod.logger.info("护身符 : 返回手卡");
        this.addToBot(new decktohandpatch(this));
    }



    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
            initializeDescription();
        }
    }

}
