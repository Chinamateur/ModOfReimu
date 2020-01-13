package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.OutbreakEffect;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import java.awt.*;

public class SpiritualOutbreak2 extends CustomCard {
    public static final String ID = "SpiritualOutbreak2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   // public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SpiritualOutbreak.png";
    private static final int COST = 1;
    private static final String A = VigorPower.POWER_ID ;
    public SpiritualOutbreak2() {
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
        this.baseDamage = 8 ;
        this.tags.add(CardTags.STRIKE);
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(p, new OutbreakEffect(p.hb.cX,p.hb.cY, new Color(1,1,1,1),1.3F),0.4F));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(
                        p,
                        this.multiDamage,
                        DamageInfo.DamageType.NORMAL,
                        AbstractGameAction.AttackEffect.FIRE
                )
        );
        /*
        if (p.hasPower(A)){
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p,p,new VigorPower(p,p.getPower(A).amount),p.getPower(A).amount)
            );
        }

         */

        //this.addToBot(new SpiritualOutbreakAction(this,this.energyOnUse, m));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeDamage(3);
            this.upgradeName();
            //this.rawDescription = DESCRIPTION_UPG ;
            initializeDescription();
        }
    }
}
