package ReimuMod.powers;

import ReimuMod.ReimuMod;
import ReimuMod.cards.Linmeng.New.FantasyBorder;
import ReimuMod.patches.AbstractCardEnum;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FantasyPower extends AbstractPower {

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("FantasyPower:ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FantasyPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = "FantasyPower:ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/powers/FantasyPower.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/FantasyPower_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/FantasyPower_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription(){
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (!(card instanceof FantasyBorder)) {
            card.unhover();
            card.lighten(true);
            card.setAngle(0.0F);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.current_x = CardGroup.DISCARD_PILE_X;
            card.current_y = CardGroup.DISCARD_PILE_Y;
            //card.purgeOnUse
            AbstractDungeon.player.discardPile.addToTop(card.makeSameInstanceOf());
            //p.hand.removeCard(card);
            AbstractDungeon.player.discardPile.refreshHandLayout();
            AbstractDungeon.player.discardPile.applyPowers();
        }
    }
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            updateDescription();
        }
    }

}
