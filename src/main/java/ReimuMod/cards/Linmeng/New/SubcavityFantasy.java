package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.DoublePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SubcavityFantasy extends CustomCard {
    public static final String ID = "SubcavityFantasy";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/SubcavityFantasy.png";
    private static final int COST = 1;


    public SubcavityFantasy() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
            (AbstractDungeon.player, AbstractDungeon.player,
                    new DoublePower(AbstractDungeon.player, this.magicNumber),  this.magicNumber
            )
        );
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            this.exhaust = false;
            initializeDescription();
        }
    }

    public int Hmany() {
        if (AbstractDungeon.player.hasPower("DoublePower:ReiMu")) {
            return AbstractDungeon.player.getPower("DoublePower:ReiMu").amount + 1;
        } else {
            return 2 ;
        }
    }

    public AbstractCard makeCopy() {
        return new SubcavityFantasy();
    }

}
