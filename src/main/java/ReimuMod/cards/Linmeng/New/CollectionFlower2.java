package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class CollectionFlower2 extends CustomCard {
    public static final String ID = "CollectionFlower2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/CollectionFlower.png";
    private static final int COST = 1;

    public CollectionFlower2() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION_UPG,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = 2;
        this.baseDamage = 0 ;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        /*
        for(AbstractPower pp : p.powers){
            if (pp.type == AbstractPower.PowerType.BUFF){
                this.baseDamage++;
            }
        }
        this.addToBot(new DamageAction(m,new DamageInfo(p,this.magicNumber*this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

         */

        if(p.hasPower(VigorPower.POWER_ID)){
            int x = p.getPower(VigorPower.POWER_ID).amount*(this.magicNumber-1);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new VigorPower(p,x),x));
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeMagicNumber(1);
            this.upgradeName();
            initializeDescription();
        }
    }
}
