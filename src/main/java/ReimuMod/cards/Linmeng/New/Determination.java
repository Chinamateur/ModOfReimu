package ReimuMod.cards.Linmeng.New;

import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class Determination extends CustomCard {
    public static final String ID = "Determination";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 1;

    public Determination() {
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
        //this.baseBlock = 2;
        this.baseDamage = 0;
        this.exhaust = true;
        //this.baseMagicNumber = this.magicNumber = 2;
        //this.isEthereal = true;
    }
    public AbstractCard makeCopy() { return new Determination(); }
    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = p.maxHealth-p.currentHealth;
        if (this.damage>0){
            x += this.damage ;
        }
        if (x>=10){
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
            if (x>=20){
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.15F));
            }
            if (x>=30){
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.05F));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p,x,this.damageTypeForTurn), x >= (p.maxHealth/2)?AbstractGameAction.AttackEffect.FIRE : AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        //AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, p.maxHealth - p.currentHealth));// + this.block
       // new setKami(this.magicNumber,"yan");
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = DESCRIPTION_UPG;
            //this.upgradeDamage();
            //this.upgradeBaseCost(0);
            //this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }

}
