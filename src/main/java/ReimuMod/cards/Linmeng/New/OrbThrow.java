package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.MyOrbEffect;
import ReimuMod.action.MINE.OrbThrowaction;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OrbThrow extends CustomCard {
    public static final String ID = "OrbThrow";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;
    public OrbThrow() {
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
        //this.baseDamage = 20;
        this.baseMagicNumber= this.magicNumber = 2 ;
        this.baseDamage = 5 ;
        //this.isEthereal = true;
        //this.isMultiDamage = true;
    }
    public AbstractCard makeCopy() {
        return new OrbThrow();
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        Color  aa = new Color(0.5F,0.2F,0.5F,1F);
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (randomMonster != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new MyOrbEffect( randomMonster.hb.cX,randomMonster.hb.cY, p.hb.cX ,p.hb.cY,aa), 0F));
            AbstractDungeon.actionManager.addToBottom(new WaitAction(1F));
            AbstractDungeon.actionManager.addToBottom(new OrbThrowaction(randomMonster,this.damage,this.magicNumber,aa));
        }
        new setKami(2,"po");
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
            //this.upgradeDamage(1);
            //this.upgradeMagicNumber(1);
            //this.isEthereal = false;
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            //this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
