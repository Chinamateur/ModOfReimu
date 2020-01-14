package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.Flyfan;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class YinYangSign extends CustomCard {
    public static final String ID = "YinYangSign";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 2;
    public YinYangSign() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY
        );
        this.baseDamage = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(ConstrictedPower.POWER_ID)){
            //AbstractDungeon.actionManager.addToTop(new VFXAction(new UpgradeShineEffect(m.hb.cX,m.hb.cY)));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p,this.damage+m.getPower(ConstrictedPower.POWER_ID).amount, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }else {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p,this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        int xz = 0 ;
        for(AbstractPower l :m.powers){
            if(l.type == AbstractPower.PowerType.DEBUFF){
                xz++;
            }
        }
        this.addToBot(new GainEnergyAction(xz));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(1);
            initializeDescription();
        }
    }
}
