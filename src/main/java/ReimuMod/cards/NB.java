package ReimuMod.cards;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.NBAction;
import ReimuMod.action.MINE.setKami;
import ReimuMod.characters.ReiMu;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NB extends CustomCard {
    public static final String ID = "NB";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String EX[] = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 2;
    public NB() { this(0); }
    public NB(int x) {
        super(
                ID+":ReiMu",
                x>0?DESCRIPTION:NAME,
                "img/Reimucards/NDoubleB"+x+".png",
                COST,
                x>0?EX[2]:EX[0],
                x>0? CardType.ATTACK:CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.BASIC,
                x>0?CardTarget.ENEMY:CardTarget.SELF
        );
        //this.tags.add(BaseModCardTags.BASIC_DEFEND);
        this.baseHeal = x;
        this.baseDamage = 7;
        this.baseBlock = 5;
        this.magicNumber = this.baseMagicNumber = 3;
        this.cardsToPreview = new NB(x==0);
    }
    public NB(boolean x) {
        super(
                ID+":ReiMu",
                x?DESCRIPTION:NAME,
                "img/Reimucards/NDoubleB"+(x?1:0)+".png",
                COST,
                x?EX[2]:EX[0],
                x? CardType.ATTACK:CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.BASIC,
                x?CardTarget.ENEMY:CardTarget.SELF
        );
        this.baseDamage = 7;
        this.baseBlock = 5;
        this.magicNumber = this.baseMagicNumber = 3;
        this.baseHeal = x?1:0;
        this.cardsToPreview = null;
    }
    @Override
    public AbstractCard makeCopy() { return new NB(this.baseHeal); }
        @Override
        public void applyPowers(){
            if (this.cardsToPreview != null) {
                if(this.baseHeal!=0&&this.baseHeal!=1){
                    this.baseHeal=0;
                }
                this.target = this.baseHeal>0?CardTarget.ENEMY:CardTarget.SELF;
                this.name = this.baseHeal>0?DESCRIPTION:NAME;
                if(this.upgraded){
                    this.rawDescription = this.baseHeal>0?EX[3]:EX[1];
                }else {
                    this.rawDescription = this.baseHeal>0?EX[2]:EX[0];
                }
                this.type = this.baseHeal>0? CardType.ATTACK:CardType.SKILL;
                this.cardsToPreview = new NB(this.baseHeal == 0);
                if(this.upgraded ){
                    this.cardsToPreview.upgrade();
                }
                this.loadCardImage("img/Reimucards/NDoubleB"+this.baseHeal+".png");
            }
            super.applyPowers();
            initializeDescription();
        }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new NBAction(this,m));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
            this.upgradeBlock(2);
            this.upgradeMagicNumber(1);
            this.rawDescription = this.baseHeal>0?EX[3]:EX[1];
            if(this.cardsToPreview!= null){
                this.cardsToPreview.upgrade();
            }
            //if(AbstractDungeon.player != null){ applyPowers(); }
            initializeDescription();
        }
    }
}
