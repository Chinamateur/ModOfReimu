package ReimuMod.cards;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;

public class KamiComing3 extends CustomCard {
    public static final int n = 3 ;
    public static final String ID = "KamiComing"+n;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("KamiComing"+":ReiMu");
    public static String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPDESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXDESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = -2;
    private static int USE;
    public KamiComing3() {
        super(
                ID+":ReiMu",
                EXDESCRIPTION[n],
                "img/Reimucards/"+ID+".png",
                COST,
                EXDESCRIPTION[n+7],
                CardType.POWER,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.exhaust = true;
    }

    public void onChoseThisOption() {
        AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber * 2, true));
        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
        String[] kami = {"炎","破","雷","智","月","风","阳"};
        setKami.getKami2 x = new setKami.getKami2();
        ReimuMod.logger.info("降神"+n+"预计获得:"+setKami.kami[n]+(setKami.max() - x.getKami2(setKami.kami[n]))+"层");
        new setKami(setKami.max() - x.getKami2(kami[n]),kami[n]);
    }
    public boolean canUpgrade(){
        return false;
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    public void use(AbstractPlayer p, AbstractMonster m) { }
    public void upgrade() { }
}
