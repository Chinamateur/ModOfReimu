package ReimuMod.cards;

import ReimuMod.action.MINE.setKami;
import ReimuMod.powers.Kami.*;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MyTest extends CustomCard {

    public static final String ID = "MyTest";
    public static final String IMG_PATH = "img/cards/MyTest.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;

    public MyTest() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                AbstractCard.CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber =10;
        this.baseDamage = 999 ;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = m.getPower("powerID").amount;
        //new setKami(1,"lei");
        //this.addToBot(new ApplyPowerAction(p, p, new FantasyPower(p, 2), 2, true, AbstractGameAction.AttackEffect.NONE));
        //new setKami(this.magicNumber,"all");
        if(p.hasRelic("CogitoBucket")){
            p.getRelic("CogitoBucket").counter += 10000 ;
        }
        new setKami(10,"all");
        /*
        if (test == 0){
            test++;
            new setKami(10,"all");
        }else{
            test = 0 ;
            for (int i =0 ; i<7 ; i++){
                new rbAction().remove(i);
            }
        }
         */

       // AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)null, this.multiDamage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
