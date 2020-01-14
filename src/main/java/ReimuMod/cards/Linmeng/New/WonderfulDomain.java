package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FastingEffect;

import java.util.ArrayList;

public class WonderfulDomain extends CustomCard {
    public static final String ID = "WonderfulDomain";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 1;
    public WonderfulDomain() {
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
        //this.baseDamage = 8;
        this.baseBlock = 7 ;
        //this.exhaust = true ;
        //this.baseMagicNumber = this.magicNumber = 2;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p != null) {
            ArrayList <Integer> l = new ArrayList<>();
            if(setKami.getKami2.getKami2("shen")>0){
                for(int s = 0 ;s<7;s++){
                    if(setKami.getKami2.getKami2(setKami.kami[s])>=setKami.max()){
                        l.add(s);
                    }
                }
            }
            if(l.size()>0){
                this.addToBot(new VFXAction(new FastingEffect(p.hb.cX, p.hb.cY,setKami.Kami10.KamiColoer(l.get((int)(Math.random()*l.size()-1))))));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        int y = setKami.getKami2.getKami2("all")/10;
        if (y>0){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, y ));
        }
    }
    public void triggerOnGlowCheck() {
        int y = setKami.getKami2.getKami2("all")/10;
        if (y>0){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(3);
            //this.upgradeMagicNumber(1);
            //this.upgradeDamage(2);
            //this.baseDamage = 14;
            //this.upgradeBaseCost(0);
            //this.isInnate =true;
            //this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
