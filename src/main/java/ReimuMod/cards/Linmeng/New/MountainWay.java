package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MountainWay extends CustomCard {
    public static final String ID = "MountainWay";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;

    public MountainWay() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY
        );
        //this.exhaust = true;
        this.baseBlock = 0;
        this.magicNumber = this.baseMagicNumber = 2 ;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = 0 ;
        for (AbstractPower z : p.powers){
            if (z.type == AbstractPower.PowerType.BUFF){
                x++;
            }
        }
        for  (AbstractPower z : m.powers){
            if (z.type == AbstractPower.PowerType.DEBUFF){
                x++;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,this.block+x));
        new setKami(this.magicNumber,"yue");
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            //this.upgradeBlock(3);
            initializeDescription();
        }
    }
}
