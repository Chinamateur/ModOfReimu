package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.PhosphorusFlamePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

public class PhosphorusFlame extends CustomCard {
    public static final String ID = "PhosphorusFlame";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 2;

    public PhosphorusFlame() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY
        );
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo,p,new PhosphorusFlamePower(mo)));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION ;
            this.upgradeBaseCost(1);
            this.upgradeName();
            initializeDescription();
        }
    }
}
