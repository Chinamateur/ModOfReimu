package ReimuMod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DoublePower extends AbstractPower {
    public static final String NAME = "DoublePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DoublePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/Reimupowers/DoublePower.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription() {
        if (this.amount > 0){
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2];
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {

        if (!card.purgeOnUse && this.amount > 0) {
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }
            this.flash();
            for( int i=this.amount ; i>0 ; i-- ) {
                AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.purgeOnUse = true;
                tmp.freeToPlayOnce = true;
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = (float)Settings.WIDTH / 2.0F - 400.0F * Settings.scale + i*100F;
                tmp.target_y = (float)Settings.HEIGHT / 2.0F;
                if (m != null) {
                    tmp.calculateCardDamage(m);
                }
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));


/*
                AbstractDungeon.player.limbo.group.add(tmp);
                tmp.current_x = (Settings.WIDTH / 2.0F);
                tmp.current_y = (Settings.HEIGHT / 2.0F);
                tmp.target_x = (Settings.WIDTH / 3.0F - 300.0F * Settings.scale - 50F*qq);
                tmp.target_y = (Settings.HEIGHT / 2F);

                tmp.purgeOnUse = true;
                tmp.freeToPlayOnce = true;
                tmp.targetAngle = 0.0F;
                tmp.drawScale = 0.12F;
                tmp.lighten(false);
                AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(true);
                AbstractDungeon.actionManager.currentAction = null;
                tmp.applyPowers();
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m));

 */
                //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card, 1));

            }
            updateDescription();

            /*
            ReimuMod.logger.info("多重完毕，层数:"+this.amount);
            if(this.amount < 1) {
                ReimuMod.logger.info("多层结束:"+this.amount);
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
            }
            */

        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner,this.ID));
        }
    }
}
