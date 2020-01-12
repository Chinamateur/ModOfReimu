package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.MoneyEffect;
import ReimuMod.action.MINE.MoneyEffect3;
import ReimuMod.action.MINE.MoneyEffect5;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

public class
BarrageOfMoneybags extends CustomCard {
    public static final String ID = "BarrageOfMoneybags";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 0;

    public BarrageOfMoneybags() {
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
        this.baseDamage = 30;
        this.baseMagicNumber = this.magicNumber = 10;
        this.baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReimuMod.logger.info("打出卡牌:"+NAME);
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new MoneyEffect3(30, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            this.addToBot(new VFXAction(new MoneyEffect3(30, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
        }
        if (!this.freeToPlayOnce) {
            if ((p.gold / 10) + EnergyPanel.totalCount >= this.baseBlock) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY,true));
                if (EnergyPanel.totalCount >= this.baseBlock) {
                    p.energy.use(this.baseBlock);
                } else {
                    int x = this.baseBlock - EnergyPanel.totalCount;
                    p.energy.use(EnergyPanel.totalCount);
                    AbstractDungeon.player.loseGold(x * 10);
                    for(int i = x * 10;i>0; i--) {
                        this.addToBot(new VFXAction(new MoneyEffect5(AbstractDungeon.player,-1)));
                    }
                }
            }
        }else {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY,false));
        }

    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean canUse = super.canUse(p, m);
        if (!canUse) { return false; }
        if (((p.gold / 10 )+ EnergyPanel.totalCount < this.baseBlock)&&!this.freeToPlayOnce) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }
    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            this.upgradeBlock(-1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            //upgradeDamage(3);
            this.upgradeName();
            initializeDescription();
        }
    }
}
