package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.HakureiPhantomPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HakureiPhantom extends CustomCard {
    public static final String ID = "HakureiPhantom";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;

    public HakureiPhantom() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                CardTarget.ENEMY
        );
        this.exhaust = true;
        //this.isEthereal = true ;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new HakureiPhantomPower(m,-1)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.exhaust = false ;
            //ReimuMod.logger.info("卡牌升级:"+ID);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION ;
            //this.upgradeBaseCost(1);
            this.upgradeName();
            initializeDescription();
        }
    }
}
