package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Free extends CustomCard {

    public static final String ID = "Free";
    public static final String IMG_PATH = "img/Reimucards/Free.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;

    public Free() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                AbstractCard.CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }


/*
    public void triggerOnExhaust() {
        AbstractPlayer p = AbstractDungeon.player;
        for(int i = this.magicNumber; i > 0;i--){
            AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, true));
        }
    }
 */
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
    return false;
}
    public void triggerWhenDrawn() {
        //new setKami(this.magicNumber,"lei");
        int i = 0 ;
        AbstractCard c= AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        ReimuMod.logger.info("少女奇想真的打出卡牌:"+c.name);
        if (this.upgraded) { c.upgrade(); }
        c.purgeOnUse = true;
        c.freeToPlayOnce = true;
        this.addToBot(new NewQueueCardAction(c, true, false, true));
        /*
        if(this.magicNumber>0&&AbstractDungeon.player.hand.size()>1) {
            AbstractCard c = null;
            this.magicNumber--;
            CardGroup stanceChoices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard a : AbstractDungeon.player.hand.group) {
                if (a.cost != -2) {
                    ReimuMod.logger.info("自由自在检索:" + a);
                    stanceChoices.addToBottom(a);
                }
            }
            if (stanceChoices.size() > 0) {
                c = stanceChoices.group.get(((int) (Math.random() * stanceChoices.group.size())));
            }
            ReimuMod.logger.info("自由自在选择:" + c);
            if (c != null) {
                c.freeToPlayOnce = true;
                switch (c.target) {
                    case SELF_AND_ENEMY:
                    case ENEMY:
                        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getRandomMonster()));
                        break;
                    case SELF:
                    case ALL:
                    case ALL_ENEMY:
                    case NONE:
                    default:
                        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, (AbstractMonster) null));
                }
            }
        }

         */
    }

    @Override
    public void use(AbstractPlayer arg0, AbstractMonster arg1) {
    }
}
