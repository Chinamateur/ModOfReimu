package ReimuMod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sakura extends CustomCard {

    public static final String ID = "Sakura";
    public static final String IMG_PATH = "img/cards/Sakura.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public Sakura() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.ENEMY
        );
        this.baseMagicNumber = this.magicNumber =3;
        this.baseBlock = 9 ;
        this.baseDamage = 9 ;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(
                        m,
                        new DamageInfo(p, this.damage, DamageInfo.DamageType.HP_LOSS),
                        AbstractGameAction.AttackEffect.FIRE
                )
        );
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,this.block));
        //AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeDamage(3);
            //this.upgradeBaseCost(0);
            //this.upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
