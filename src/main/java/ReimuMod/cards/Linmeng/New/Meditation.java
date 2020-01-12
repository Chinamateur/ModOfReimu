package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.Kami.Effect.MedEffect;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightRayFlyOutEffect;

public class Meditation extends CustomCard {
    public static final String ID = "Meditation";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 0;
    public Meditation() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        //this.baseDamage = 14;
        this.baseMagicNumber= this.magicNumber = 2;
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectsQueue.add(new MedEffect(p.hb.cX, p.hb.cY, setKami.Kami10.KamiColoer(2)));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber-1));
        this.addToTop(new SFXAction("HEAL_1"));
        new setKami(this.magicNumber,"zhi");
    }
@Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        for(AbstractCard c : p.hand.group){
            if (c.type != CardType.SKILL){
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return  false;
            }
        }
        return true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
            //this.isEthereal = false;
            //this.upgradeBaseCost(4);
            //this.isInnate =true;
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
