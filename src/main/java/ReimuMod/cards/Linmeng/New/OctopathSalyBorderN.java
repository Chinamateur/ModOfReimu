package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OctopathSalyBorderN extends CustomCard {
    public static final String ID = "OctopathSalyBorderN";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/OctopathSalyBorder.png";
    private static final int COST = 2;
    public OctopathSalyBorderN() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                CardTarget.ALL_ENEMY
        );
        this.baseDamage = 26;
        //this.baseMagicNumber= this.magicNumber = 1 ;
        this.isMultiDamage = true;
        //this.baseMagicNumber = this.magicNumber =  2;
    }
    public AbstractCard makeCopy() {
        return new OctopathSalyBorderN();
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        //setKami.getKami2 j = new setKami.getKami2();
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction( p,
                this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        /*
        int q = j.getKami2("shen");
        int z = 0 ;
        for(AbstractOrb o : p.orbs) {
            if (!(o instanceof EmptyOrbSlot)) {
                z++;
            }
        }
        while (q>0&&z>0) {
            if (AbstractDungeon.player.orbs.get(0) instanceof KamiYanOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiPoOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiZhiOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiYueOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiLeiOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiFengOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiYangOrb) {
                AbstractDungeon.player.removeNextOrb();
                q--;
                z--;
            } else {
                for (int i = 1 ; i != z ;i++){
                    Collections.swap(AbstractDungeon.player.orbs, i, i-1);
                }
            }
        }
         */
        new setKami(-999,"all");
        //AbstractDungeon.actionManager.addToBottom(new RemoveAllOrbsAction());
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            //this.rawDescription = DESCRIPTION_UPG;
            this.upgradeDamage(8);
            initializeDescription();
        }
    }
}
