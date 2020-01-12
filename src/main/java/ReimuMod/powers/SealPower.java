package ReimuMod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SealPower extends AbstractPower {
    public static final String NAME = "SealPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SealPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/SealPower_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/SealPower_128.png"),0,0,128,128);
        this.type = PowerType.DEBUFF;

    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 0.5F;
        } else {
            return damage;
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        return damageAmount;
    }
}
