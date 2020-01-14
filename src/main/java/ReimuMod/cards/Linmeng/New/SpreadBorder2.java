package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import java.util.Iterator;


public class SpreadBorder2 extends CustomCard {
    public static final String ID = "SpreadBorder2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/SpreadBorder.png";
    private static final int COST = 1;
   // private static final int M = 5;


    public SpreadBorder2() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.baseMagicNumber= this.magicNumber = 2 ;
        this.baseBlock = 5 ;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY,setKami.Kami10.KamiColoer(4), ShockWaveEffect.ShockWaveType.ADDITIVE), 0.25F));
        new setKami(2, "yue");
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,this.block));

        Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();
        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof SpreadBorder2) {
                c.baseBlock += this.magicNumber;
                c.applyPowers();
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof SpreadBorder2) {
                c.baseBlock += this.magicNumber;
                c.applyPowers();
            }
        }

        var1 = AbstractDungeon.player.hand.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof SpreadBorder2) {
                c.baseBlock += this.magicNumber;
                c.applyPowers();
            }
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(3);
            //this.upgradeMagicNumber(1);
            initializeDescription();
        }
    }

}
