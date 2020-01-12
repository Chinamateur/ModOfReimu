package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class AlertArray extends CustomCard {
    public static final String ID = "AlertArray";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 2;
    public AlertArray() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.baseBlock = 8;
        this.baseMagicNumber= this.magicNumber = 5;
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block)
        );
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new VigorPower(p,this.magicNumber),this.magicNumber));
        if (this.upgraded){
            new setKami(3,"po");
        }else {
            new setKami(2,"po");
        }
        /*
        int x = 0 ;
        for (AbstractMonster i : AbstractDungeon.getMonsters().monsters){
            if (!i.isDead || i.halfDead){
                x++;
            }
        }
        new setKami(x,"yue");
         */
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(4);
            //this.upgradeBaseCost(3);
            this.upgradeMagicNumber(3);
            //this.isEthereal = false;
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
