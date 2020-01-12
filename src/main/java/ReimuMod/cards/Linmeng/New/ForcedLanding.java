package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import java.util.Iterator;

public class ForcedLanding extends CustomCard {
    public static final String ID = "ForcedLanding";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;
    public ForcedLanding() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY
        );
        this.baseDamage = 17;
        this.baseMagicNumber= this.magicNumber = 2 ;
        //this.isEthereal = true;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("Flyfan:ReiMu")) {
            if(!p.hasPower("ShadowOfCrowPower:ReiMu")){
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction( p, p,"Flyfan:ReiMu", 1));
            }
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect((Settings.WIDTH / 1.5F), (Settings.HEIGHT / 2.0F))));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction( p,
                    this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            new setKami(this.magicNumber,"lei");
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if (!p.hasPower("Flyfan:ReiMu")) {
            this.cantUseMessage = DESCRIPTION_UPG;
            return false;
        }
        return true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeDamage(4);
            //this.isEthereal = false;
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            //this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
