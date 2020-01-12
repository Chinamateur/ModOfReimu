package ReimuMod.cards;

import ReimuMod.action.MINE.MoneyEffect;
import ReimuMod.action.MINE.setKami;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Poor extends CustomCard {
    public static final String[] kami = setKami.kami;
    public static final String ID = "Poor";
   //public static final String IMG_PATH = "img/cards/BodyOfKami.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXDESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = -2;

    public Poor() {
        super(
                ID+":ReiMu",
                NAME,
                "img/cards/"+ID+".png",
                COST,
                DESCRIPTION,
                CardType.CURSE,
                CardColor.CURSE,
                CardRarity.CURSE,
                CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = 20;
        this.isEthereal = true;
    }

    public void upgrade() {
        /*
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }

         */
    }
    @Override
    public boolean canUpgrade(){
        return false;
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
/*
    public void triggerOnExhaust() {
        AbstractPlayer p = AbstractDungeon.player;
        for(int i = this.magicNumber; i > 0;i--){
            AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, true));
        }
    }
 */
    public void triggerOnEndOfTurnForPlayingCard() {
        
        this.dontTriggerOnUseCard = true;
        int x = Math.min(AbstractDungeon.player.gold,this.magicNumber);
        AbstractDungeon.player.loseGold(x);
        while (x > 0 ){
            AbstractDungeon.effectList.add(
                    new MoneyEffect(
                            AbstractDungeon.player,
                            -1
                    )
            );
            x --;
        }
    }
    public void triggerWhenDrawn() {
        this.addToBot(new SetDontTriggerAction(this, false));
    }

    @Override
    public void use(AbstractPlayer arg0, AbstractMonster arg1) {    }
}

