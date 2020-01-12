package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;

public class Authority extends CustomCard {
    public static final String ID = "Authority";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST =1;
    private static final  setKami.getKami2 j = new setKami.getKami2();
    public Authority() {
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

        this.baseMagicNumber = this.magicNumber = 5;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new RainbowCardEffect()));
        if (j.getKami2("yang")>= setKami.max()){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new BufferPower(p,1),1));
        }
        new setKami(this.magicNumber,"yang");
        //AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        /*
        if (!Settings.FAST_MODE) {AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block)
        );}
         */
    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (j.getKami2("yang")>= setKami.max()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            //this.upgradeMagicNumber(2);
            //this.upgradeBlock(2);
            initializeDescription();
        }
    }
}
