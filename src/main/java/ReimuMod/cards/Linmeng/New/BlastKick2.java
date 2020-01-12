package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlastKick2 extends CustomCard {
    public static final String ID = "BlastKick2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BlastKick.png";
    private static final int COST = 1;
    public int counter = 0;

    @Override
    public void applyPowers() {
        counter = ReimuMod.FlyCounter;
        this.rawDescription = (EXTENDED_DESCRIPTION[0]+counter+EXTENDED_DESCRIPTION[1]);
        initializeDescription();
        super.applyPowers();
    }
    @Override
    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = (EXTENDED_DESCRIPTION[0]+(counter+this.magicNumber)+EXTENDED_DESCRIPTION[1]);
        initializeDescription();
    }

    public BlastKick2() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                CardTarget.ENEMY
        );
        //this.exhaust =true;
        this.baseDamage = 6;
        this.baseMagicNumber = this.magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(ReimuMod.FlyCounter>0){
            if (!Settings.FAST_MODE) {
                AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
            for (int x1 = ReimuMod.FlyCounter+this.magicNumber; x1 > 0; x1--) {
/*
                AbstractCard card = new UpKicking().makeCopy();
                if (this.upgraded) {
                    card.upgrade();
                }
                AbstractDungeon.player.limbo.group.add(card);
                card.current_x = (Settings.WIDTH / 2.0F);
                card.current_y = (Settings.HEIGHT / 2.0F);
                card.target_x = (Settings.WIDTH / 3.0F - 300.0F * Settings.scale - 50F*qq);
                card.target_y = (Settings.HEIGHT / 1.5F);
                qq++;
                //AbstractDungeon.player.hand.addToHand(card);
                card.purgeOnUse = true;
                card.targetAngle = 0.0F;
                card.drawScale = 0.12F;
                card.lighten(false);
                AbstractDungeon.actionManager.currentAction = null;
                //card.retain = true;
                card.applyPowers();
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(card, null));
 */
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(
                                m,
                                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                                AbstractGameAction.AttackEffect.BLUNT_LIGHT
                        )
                );
                //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card, 1));
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            upgradeDamage(2);
            //this.upgradeMagicNumber(1);
            //this.exhaust = true;
            //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION ;
            this.upgradeName();
            initializeDescription();
        }
    }
}
