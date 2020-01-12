package ReimuMod.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class Strike_Lin extends CustomCard {
  public static final String ID = "Strike_Lin";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/Strike_Lin.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 6;
  private static final int UPGRADE_PLUS_DMG = 3;

  public Strike_Lin() {
    super(
        ID+":ReiMu",
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        CardType.ATTACK,
        AbstractCardEnum.REIMU_COLOR,
            AbstractCard.CardRarity.BASIC,
        CardTarget.ENEMY
    );
    this.tags.add(BaseModCardTags.BASIC_STRIKE);
    this.tags.add(CardTags.STARTER_STRIKE);
    this.tags.add(CardTags.STRIKE);
    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        )
    );
  }

  @Override
  public boolean isStrike() {
    return true;
  }

  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}