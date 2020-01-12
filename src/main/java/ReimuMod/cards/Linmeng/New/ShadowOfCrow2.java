package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.Flyfan;
import ReimuMod.powers.ShadowOfCrowPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ShadowOfCrow2 extends CustomCard {
    public static final String ID = "ShadowOfCrow2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ShadowOfCrow.png";
    private static final int COST = 1;

    public ShadowOfCrow2() {
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
        this.baseMagicNumber = this.magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReimuMod.logger.info("打出卡牌:" + ID);
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ShadowOfCrowPower(p,this.magicNumber),this.magicNumber));
        for(AbstractMonster mo:AbstractDungeon.getCurrRoom().monsters.monsters){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo,p,new ConstrictedPower(mo,p,this.magicNumber),this.magicNumber));
            if(p.hasPower("Flyfan:ReiMu")){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo,p,new WeakPower(mo,1,false),1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo,p,new VulnerablePower(mo,1,false),1));
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            //this.exhaust=false;
            //ReimuMod.logger.info("卡牌升级:"+ID);
            //this.upgradeDamage(2);
            this.upgradeMagicNumber(2);
            //this.upgradeBaseCost(0);
            this.upgradeName();
            initializeDescription();
        }
    }
}
