package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.Flyfan;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Stagnation extends CustomCard {
    public static final String ID = "Stagnation";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    public static final String POWER = "Flyfan:ReiMu";
    private static final int COST = 1;

    public Stagnation() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.BASIC,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = 2 ;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReimuMod.logger.info("打出卡牌:"+ID);
        if (p.hasPower(POWER)){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new Flyfan(p,1),1));
        }
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower("Flyfan")) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            //upgradeBaseCost(0);
            //this.retain = true ;
            //this.isEthereal =false ;
            this.upgradeMagicNumber(1);
            //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION ;
            this.upgradeName();
            initializeDescription();
        }
    }
}
