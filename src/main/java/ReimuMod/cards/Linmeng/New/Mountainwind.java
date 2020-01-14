package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.TypeDisToHandAction;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.Flyfan;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Mountainwind extends CustomCard {
    public static final String ID = "Mountainwind";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 2;
    public Mountainwind() {
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
        //this.baseDamage = 8;
        this.baseMagicNumber= this.magicNumber = 1 ;
        //this.baseDamage= 9 ;
        this.exhaust =true ;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup stanceChoices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new Flyfan(p,1),1));
        for (AbstractCard c : p.discardPile.group ){
            if (c.type == CardType.ATTACK ){
                ReimuMod.logger.info("深山行动："+c+"加入列表");
                stanceChoices.addToBottom(c);
            }
        }
        if (!stanceChoices.isEmpty()) {
            AbstractDungeon.actionManager.addToTurnStart(new TypeDisToHandAction(this.magicNumber,stanceChoices,true));
        }

        /*
        new setKami(this.magicNumber,"feng");
        Iterator c;
        c = AbstractDungeon.getMonsters().monsters.iterator();
        AbstractMonster moo;
        while (c.hasNext()) {
            moo = (AbstractMonster) c.next();
            if (moo != null && !moo.isDead && moo.currentHealth>0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(moo,p, new WeakPower(moo,this.magicNumber,false),this.magicNumber));
            }
        }

         */
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.upgradeBlock(1);
            //this.upgradeMagicNumber(1);
            //this.baseDamage = 14;
            this.exhaust = false ;
            this.rawDescription = DESCRIPTION_UPG;
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            initializeDescription();
        }
    }
}
