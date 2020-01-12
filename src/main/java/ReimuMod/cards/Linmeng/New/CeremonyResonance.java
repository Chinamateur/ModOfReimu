package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

public class CeremonyResonance extends CustomCard {
    public static final String ID = "CeremonyResonance";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String EX[] = cardStrings.EXTENDED_DESCRIPTION;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST =3;
    public CeremonyResonance() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                AbstractCard.CardTarget.SELF
        );
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup G = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c:
                AbstractDungeon.player.exhaustPile.group
                //AbstractDungeon.actionManager.cardsPlayedThisCombat
        ){
            if (c.name.contains(EX[0]) ||c.name.contains(EX[1])){
                ReimuMod.logger.info("神仪复咏：找到了"+c);
                G.addToTop(c.makeSameInstanceOf());
            }
        }
        for(AbstractCard c : G.group){
            ReimuMod.logger.info("神仪复咏：打出"+c);
            c.purgeOnUse=true;
            this.addToBot(new NewQueueCardAction(c, true, true, true));
        }
    }
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        for (AbstractCard c:
                AbstractDungeon.player.exhaustPile.group
                //AbstractDungeon.actionManager.cardsPlayedThisCombat
        ){
            if (c.name.contains(EX[0])||c.name.contains(EX[1])){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
            initializeDescription();
        }
    }
}
