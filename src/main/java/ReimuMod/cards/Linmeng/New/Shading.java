package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.ShadingPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Shading extends CustomCard {
    public static final String ID = "Shading";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;
    public Shading() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                AbstractCard.CardTarget.SELF
        );
        this.baseMagicNumber= this.magicNumber = 1 ;
        this.exhaust = true ;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
                (
                        p, p, new ShadingPower(AbstractDungeon.player, this.magicNumber), this.magicNumber
                )
        );
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(0);
            //upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
