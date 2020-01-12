package ReimuMod.cards;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.MyChoseAction;
import ReimuMod.action.MINE.setKami;
import ReimuMod.cards.Linmeng.New.SolemnJudgment;
import ReimuMod.cards.Linmeng.New.SolemnWishes;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ComingRite extends CustomCard {
    public static final String ID = "ComingRite";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 1;

    public ComingRite() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReimuMod.logger.info("打出卡牌:"+ID);
        CardGroup stanceChoices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        if(setKami.getKami2.getKami2("yan")<setKami.max()){stanceChoices.addToBottom(new KamiComing0());}
        if(setKami.getKami2.getKami2("po")<setKami.max()){stanceChoices.addToBottom(new KamiComing1());}
        if(setKami.getKami2.getKami2("lei")<setKami.max()){stanceChoices.addToBottom(new KamiComing2());}
        if(setKami.getKami2.getKami2("zhi")<setKami.max()){stanceChoices.addToBottom(new KamiComing3());}
        if(setKami.getKami2.getKami2("yue")<setKami.max()){stanceChoices.addToBottom(new KamiComing4());}
        if(setKami.getKami2.getKami2("feng")<setKami.max()){stanceChoices.addToBottom(new KamiComing5());}
        if(setKami.getKami2.getKami2("yang")<setKami.max()){stanceChoices.addToBottom(new KamiComing6());}
        if (stanceChoices.size()==0){
            stanceChoices.addToBottom(new SolemnWishes());
            stanceChoices.addToBottom(new SolemnJudgment());
            //this.addToBot(new MyChoseAction(this.magicNumber,stanceChoices,false));
        }//else {
            //this.addToBot(new ChooseOneAction(stanceChoices.group));
        //}
        this.addToBot(new ChooseOneAction(stanceChoices.group));
    }

    public void upgrade() {
        if (!this.upgraded) {
            //this.upgradeDamage(2);
            //this.selfRetain = true ;
            //this.rawDescription = DESCRIPTION_UPG;
            //this.upgradeMagicNumber(1);
            this.upgradeBaseCost(0);
            this.upgradeName();
            initializeDescription();
        }
    }
}
