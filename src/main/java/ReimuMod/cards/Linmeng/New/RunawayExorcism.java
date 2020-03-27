package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;

public class RunawayExorcism extends CustomCard {
    public static final String ID = "RunawayExorcism";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 2;
    public RunawayExorcism() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                CardTarget.ALL_ENEMY
        );
        //this.baseDamage = 4 ;
        this.baseMagicNumber= this.magicNumber = 3;
        //this.exhaust = true ;
        //this.isMultiDamage = true;
    }
    public AbstractCard makeCopy() { return new RunawayExorcism(); }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //int pz = 0 ;
        int x = Math.min(p.drawPile.group.size(),this.magicNumber);
        if(x>0){
            CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
            while (x>0) {
                AbstractCard a = p.drawPile.getTopCard();
                if (a.type == CardType.ATTACK) {
                    AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
                    AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Wrath"));
                    a.freeToPlayOnce = true;
                    AbstractDungeon.player.drawPile.removeCard(a);
                    this.addToBot(new NewQueueCardAction(a, true, true, true));
                } else {
                    AbstractDungeon.player.drawPile.moveToDiscardPile(a);
                }
                x--;
            }
        }

        //new setKami(pz,"yan");
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            //this.rawDescription = DESCRIPTION_UPG;
            //upgradeDamage(5);
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }
}

