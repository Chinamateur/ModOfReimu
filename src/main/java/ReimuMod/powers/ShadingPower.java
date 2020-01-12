package ReimuMod.powers;

import ReimuMod.action.MINE.ShadingEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;

public class ShadingPower extends AbstractPower {

    public static final String POWER_ID = "ShadingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID+":ReiMu");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    //private boolean justApplied = false;
    private float particleTimer = 0.2F;
    private boolean bbb = true;
    public ShadingPower(AbstractCreature owner,int A) {
        this.name = NAME;
        this.ID = POWER_ID+":ReiMu";
        this.owner = owner;
        this.amount = A;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        this.priority = 100;
        //this.img = new Texture("img/powers/"+POWER_ID+".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/ShadingPower_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/powers/ShadingPower_128.png"),0,0,128,128);
    }
    public void updateParticles() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.2F;
                AbstractDungeon.effectsQueue.add(new ShadingEffect());
            }
        }
    }
    //描述文本
    public void updateDescription() {
        if (this.amount>0){
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] ;
        }else {
            this.description = DESCRIPTIONS[0] ;
        }
    }

    //回合开始時移除
    @Override
    public void atStartOfTurn() {
        if (this.amount == 1){
            this.amount = -1;
            this.addToBot(new PressEndTurnButtonAction());
        }else if (this.amount > 1) {
            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }else {
            AbstractDungeon.actionManager.addToTurnStart(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        updateDescription();
    }
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (this.amount < 0){ return 0; }
        return damageAmount;
    }
}
