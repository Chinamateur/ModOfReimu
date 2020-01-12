package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.ArrayList;

public class SpringWay extends CustomCard {
    public static final String ID = "SpringWay";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = -2;

    public SpringWay() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.COMMON,
                CardTarget.ENEMY
        );
        this.baseDamage = 3;
        this.baseMagicNumber = this.magicNumber = 1;
        this.isMultiDamage = true;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }
    public void triggerWhenDrawn() {
        ReimuMod.logger.info("打出卡牌:"+ID);
        int a = 0;
        String[] kami = {"炎","破","智","月","雷","风","阳"};
        ArrayList<String> list1 = new ArrayList<>();
        setKami.getKami2 x = new setKami.getKami2();
        for (String i : kami) {
            if (x.getKami2(i) < setKami.max() && x.getKami2(i)>=a ) {
                if (x.getKami2(i)>a){ list1.clear();}
                list1.add(i);
                a = x.getKami2(i) ;
            }
        }
        if (list1.size()>0){
            int z = AbstractDungeon.cardRng.random( list1.size()-1);
            new setKami(this.magicNumber, list1.get(z));
        }
        AbstractPlayer p = AbstractDungeon.player;
        if(!this.upgraded){
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p,p,new VigorPower(p,3),3)
            );
        }else {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p,p,new VigorPower(p,4),4)
            );
        }

        //AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.baseDamage, true), DamageInfo.DamageType.HP_LOSS, AbstractGameAction.AttackEffect.FIRE, true));
    }


    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            //this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
            this.upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
