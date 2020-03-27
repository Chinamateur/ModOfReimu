package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.powers.Flyfan;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class ZunPunch extends CustomCard {
    public static final String ID = "ZunPunch";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/ZunPunch.png";
    private static final int COST = 1;
    public static final String[] EXDESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public ZunPunch(int x) {
        super(
                ID+":ReiMu",
                EXDESCRIPTION[0],
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                //AbstractCardEnum.REIMU_COLOR,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.ALL_ENEMY
        );
        //this.baseDamage = 0;
        this.baseMagicNumber= this.magicNumber = 2 ;
        this.cardsToPreview = new Dazed();
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }
    public ZunPunch() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                //AbstractCardEnum.REIMU_COLOR,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.ALL_ENEMY
        );
        //this.baseDamage = 0;
        this.baseMagicNumber= this.magicNumber = 3 ;
        this.cardsToPreview = new Dazed();
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int x = this.magicNumber ;x>0;x--){
            int roll = AbstractDungeon.cardRandomRng.random(99);
            CardRarity cardRarity;
            if (roll < 50) {
                cardRarity = CardRarity.COMMON;
            } else if (roll < 80) {
                cardRarity = CardRarity.UNCOMMON;
            } else {
                cardRarity = CardRarity.RARE;
            }
            AbstractCard tmp = CardLibrary.getAnyColorCard(CardType.ATTACK, cardRarity).makeCopy();
            tmp.freeToPlayOnce = true ;
            tmp.purgeOnUse = true;
            if (this.upgraded) {
                tmp.upgrade();
            }
            AbstractDungeon.player.limbo.addToBottom(tmp);
            if (this.magicNumber == 3){
                tmp.target_x = ((float)Settings.WIDTH / 2.0F) + ((x-2)*((float)Settings.WIDTH/4.0F));
            }else {
                tmp.target_x = ((float)Settings.WIDTH - 512F ) / this.magicNumber * x ;
            }
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            this.addToBot(new NewQueueCardAction(tmp, true, true, true));
        }
        this.addToBot(new MakeTempCardInDiscardAction(new Dazed(), 1));
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
