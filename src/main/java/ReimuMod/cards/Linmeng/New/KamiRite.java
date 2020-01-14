package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class KamiRite extends CustomCard {
    public static final String ID = "KamiRite";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    private static final setKami.getKami2 j = new setKami.getKami2();
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/KamiRite.png";
    private static final int COST = 2;
    public KamiRite() {
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
        //this.baseDamage = 8;
        //this.baseMagicNumber= this.magicNumber = 2;
        //this.isEthereal = true ;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(Math.min(j.getKami2("shen"),this.magicNumber)));
        //if (!this.freeToPlayOnce) { p.energy.use(Math.min(EnergyPanel.totalCount, (this.magicNumber - j.getKami2("shen")))); }
        if (j.getKami2("shen")<7) {
            ArrayList<String> list1 = new ArrayList<>();
            for (String n : setKami.kami) {
                if (j.getKami2(n) < setKami.max()) { list1.add(n); }
            }
            String k = list1.get (AbstractDungeon.cardRandomRng.random(list1.size()-1));
            int a = setKami.max() - j.getKami2(k);
            ReimuMod.logger.info("三位一体:决定降临" + k + "; 预计获取层数:" + a);
            if (a > 0) { new setKami(a, k); }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(1);
            initializeDescription();
        }
    }
}
