package ReimuMod.powers;

import ReimuMod.relics.MINE.Salt;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SaltPower extends AbstractPower {
    public static final String NAME = "SaltPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int s1 = Salt.s1;
    public SaltPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        //this.img = new Texture("img/powers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/" + NAME + "_128.png"),0,0,128,128);
        this.type = PowerType.BUFF;

    }
    @Override
    public void stackPower(int stackAmount) {
        updateDescription();
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + s1 + DESCRIPTIONS[1];
    }
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.type == AbstractPower.PowerType.DEBUFF && !power.ID.equals("Shackled") && source == this.owner && target != this.owner && !target.hasPower("Artifact")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(this.owner, s1, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
        }
    }
}
