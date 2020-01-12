package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.SealNeedleaction;
import ReimuMod.action.MINE.sealEffect;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;

public class SealNeedle extends CustomCard {
    public static final String ID = "SealNeedle";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;

    public SealNeedle() {
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
        this.exhaust = true ;
        this.baseMagicNumber = this.magicNumber = 2 ;
        this.baseDamage = 1 ;
    }
    public AbstractCard makeCopy() {
        return new SealNeedle();
    }
    public void use(AbstractPlayer p, AbstractMonster m) {

        for(int x = this.magicNumber;x>0;x--){
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIFF_1", 0.2F));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_FAST", 0.2F));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, -200.0F, 250.0F, 3.0F, Color.WHITE, Color.PINK)));
            //AbstractDungeon.effectList.add(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, -200.0F, 250.0F, 3.0F, Color.WHITE, Color.PINK));
            AbstractDungeon.actionManager.addToBottom(new SealNeedleaction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),1));
        }


        /*
        for(int x = this.magicNumber;x>0;x--){
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIFF_1", 0.2F));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_FAST", 0.2F));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, -200.0F, 250.0F, 3.0F, Color.WHITE, Color.PINK)));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,AbstractDungeon.player, new StrengthPower(m, -1), -1));
        }

         */
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            //this.rawDescription = DESCRIPTION2;
            initializeDescription();
        }
    }
}
