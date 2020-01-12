package ReimuMod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import ReimuMod.patches.AbstractCardEnum;

public class Defend_Lin extends CustomCard {

  public static final String ID = "Defend_Lin";
  public static final String IMG_PATH = "img/cards/Defend_Lin.png";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 5;
  private static final int UPGRADE_PLUS_BLOCK = 3;

  public Defend_Lin() {
    super(
        ID+":ReiMu",
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.SKILL,
        AbstractCardEnum.REIMU_COLOR,
        AbstractCard.CardRarity.BASIC,
        AbstractCard.CardTarget.SELF
    );
    this.tags.add(BaseModCardTags.BASIC_DEFEND);
    this.baseBlock = BLOCK_AMT;
    this.tags.add(CardTags.STARTER_DEFEND);
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new GainBlockAction(p, p, this.block)
    );
  }

  public AbstractCard makeCopy() {
    return new Defend_Lin();
  }

  @Override
  public boolean isDefend() {
    return true;
  }

  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
  }
}