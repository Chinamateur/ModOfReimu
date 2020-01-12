package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Revelation extends CustomCard {
    public static final String ID = "Revelation";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    //public static final String POWER = "Flyfan";
    private static final int COST = 0;

    public Revelation() {
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
        this.baseMagicNumber = this.magicNumber = 1 ;
        // 神启
    }
    public AbstractCard makeCopy() { return new Revelation(); }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReimuMod.logger.info("打出卡牌:"+ID);
        AbstractDungeon.actionManager.addToBottom(new ScryAction(this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,1));
        new setKami(this.magicNumber,"yang");
    }

    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            //upgradeBaseCost(0);
            upgradeMagicNumber(1);
            //this.isEthereal =false ;
            //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION ;
            this.upgradeName();
            initializeDescription();
        }
    }
}
