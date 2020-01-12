package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;

public class Sacrifice extends CustomCard {
    public static final String ID = "Sacrifice";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 0;

    public Sacrifice() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReimuMod.logger.info("打出卡牌:"+NAME);
        AbstractCard c;
        ArrayList<AbstractCard> list1 = new ArrayList<>();
        if (!p.hand.isEmpty()){
            Iterator pp = p.hand.group.iterator();
            while (pp.hasNext()){
                c = (AbstractCard) pp.next();
                ReimuMod.logger.info("祭品:检测手牌"+c);
                if (c != null && c.type != CardType.ATTACK && c.uuid != this.uuid){
                    ReimuMod.logger.info("祭品:"+c+"不是攻击。");
                    list1.add(c);
                }
            }
            if (list1.size()>0){
                for (int n = list1.size();n>0;n--)
                    if (list1.size()>0){
                        AbstractCard i = list1.get(n-1);
                        ReimuMod.logger.info("祭品:准备删除"+i.name);
                        p.hand.moveToExhaustPile(i);
                        ReimuMod.logger.info("祭品:"+i.name+"已删除。");
                    }
                new setKami(list1.size()*this.magicNumber,"yan");
            }
        }
        list1.clear();
    }

    public void upgrade() {
        if (!this.upgraded) {
            //ReimuMod.logger.info("卡牌升级:"+ID);
            //this.upgradeDamage(2);
            //this.upgradeMagicNumber(1);
            this.upgradeMagicNumber(1);
            this.upgradeName();
            initializeDescription();
        }
    }
}
