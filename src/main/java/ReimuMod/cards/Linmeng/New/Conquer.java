package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.MyManaEffect;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Conquer extends CustomCard {
    public static final String ID = "Conquer";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 2;

    public Conquer() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.COMMON,
                CardTarget.ALL_ENEMY
        );
        //this.exhaust = true;
        this.baseDamage = 14;
        this.baseMagicNumber = this.magicNumber = 3;
        this.isMultiDamage = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_DEFECT_BEAM"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new MyManaEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player,this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        new setKami(this.magicNumber,"yan");
    }

    public void upgrade() {
        if (!this.upgraded) {
            //this.upgradeBaseCost(0);
            //this.exhaust=false;
            //ReimuMod.logger.info("卡牌升级:"+ID);
            this.upgradeDamage(4);
            this.upgradeMagicNumber(1);
            this.upgradeName();
            initializeDescription();
        }
    }
            /*
        int cnt = EnergyPanel.totalCount;
        if (!this.freeToPlayOnce) {
            p.energy.use(cnt);
        }
        if (p.hasRelic("Chemical X")) {
            cnt += 2;
        }
        cnt *= this.magicNumber;
        setKami.getKami2 j = new setKami.getKami2();
        new setKami(cnt,"yan");
        cnt += j.getKami2("yan");
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p,new ConquerPower(m,this.magicNumber)));

        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(cnt, false), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
         */
}
