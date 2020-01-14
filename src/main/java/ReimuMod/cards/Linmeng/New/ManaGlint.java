package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.myMagicaction;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.badlogic.gdx.math.CatmullRomSpline.calculate;

public class ManaGlint extends CustomCard {
    public static final String ID = "ManaGlint";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 0;

    public ManaGlint() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY
        );
        this.isMultiDamage = true;
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseDamage = 0;
        //this.tags.add(CardTags.STRIKE);
    }
    public AbstractCard makeCopy() { return new ManaGlint(); }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new myMagicaction(this));
    }

    public void upgrade() {
        if (!this.upgraded) {
            //this.upgradeMagicNumber(1);
            this.rawDescription = DESCRIPTION_UPG;
            this.upgradeName();
            initializeDescription();
        }
    }
}
