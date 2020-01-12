package ReimuMod.cards.Linmeng.New;

import ReimuMod.cards.Sign;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.GatheringPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Gathering extends CustomCard {
    public static final String ID = "Gathering";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 0;
    public Gathering() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                AbstractCard.CardTarget.SELF
        );
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
                (AbstractDungeon.player, AbstractDungeon.player,
                        new GatheringPower(AbstractDungeon.player)
                )
        );
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true ;
            this.rawDescription = DESCRIPTION_UPG ;
            //upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
