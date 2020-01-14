package ReimuMod.powers;

import ReimuMod.ReimuMod;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PhosphorusFlamePower extends AbstractPower {

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PhosphorusFlamePower:ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public PhosphorusFlamePower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = "PhosphorusFlamePower:ReiMu" ;
        this.owner = owner ;
        this.amount = -1 ;
        this.updateDescription() ;
        this.type = PowerType.DEBUFF;
        //this.img = new Texture("img/Reimupowers/PhosphorusFlamePower.png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/PhosphorusFlamePower_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/PhosphorusFlamePower_128.png"),0,0,128,128);
    }

    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.NORMAL) {
            ReimuMod.logger.info("鬼神磷火术发动，将"+info+"翻倍。");
            this.flash();
            return damageAmount * 2;
        }
        return damageAmount;
    }
}
