package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import ReimuMod.relics.MINE.BodyOfKamiR;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BodyOfKami extends CustomCard {
    public static final String[] kami = {"炎","破","雷","月","山","风","阳"};
    private static final String[] kami2 = {"Flame","Break","Thunderbolt","Moon","Mountain","Wind","Sun"};
    private static final String[] kami3 = {"yan","po","lei","zhi","yue","feng","yang"};
    public static final String ID = "BodyOfKami";
   //public static final String IMG_PATH = "img/Reimucards/BodyOfKami.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXDESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = -2;

    public BodyOfKami(int use) {
        super(
                ID+":ReiMu",
                NAME,
                use>=0 && use<=6 ? "img/Reimucards/"+ID+use+".png":"img/Reimucards/"+ID+".png",
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                AbstractCard.CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = 5;
        this.baseHeal = this.heal = use;
        this.isEthereal = true;
        if (use>=0 && use<=6) {
            if (Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) {
                this.rawDescription =  EXDESCRIPTION[0]+kami[use]+DESCRIPTION_UPG;
            }else {
                this.rawDescription =  EXDESCRIPTION[0]+kami2[use]+DESCRIPTION_UPG;
            }
        }
        initializeDescription();
    }

    public AbstractCard makeCopy() { return new BodyOfKami(this.baseHeal); }
    public void upgrade() {
        /*
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }

         */
    }
    public void onChoseThisOption() {
        ReimuMod.logger.info("御神体选择:"+this.baseHeal);
        if(AbstractDungeon.player.hasRelic("BodyOfKamiR:ReiMu")){
            AbstractDungeon.player.getRelic("BodyOfKamiR:ReiMu").setCounter(-this.baseHeal);
        }
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
        if (this.baseHeal>=0){
            this.dontTriggerOnUseCard = true;
            new setKami(this.magicNumber,kami3[this.baseHeal]);
        }
    }

    @Override
    public void use(AbstractPlayer arg0, AbstractMonster arg1) {
    }
}

