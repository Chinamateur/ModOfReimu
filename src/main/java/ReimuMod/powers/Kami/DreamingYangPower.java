package ReimuMod.powers.Kami;

import ReimuMod.ReimuMod;
import ReimuMod.cards.Sakura;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ConeEffect;

public class DreamingYangPower extends AbstractPower {
    public static final String NAME = "DreamingYangPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME+":ReiMu");
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static int upnub = 0 ;
    private float particleTimer2;
    public DreamingYangPower(AbstractCreature owner, int amount,boolean up) {
        this.name = powerStrings.NAME;
        this.ID = NAME+":ReiMu";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        //this.img = new Texture("img/Reimupowers/" + NAME + ".png");
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_48.png"),0,0,48,48);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("img/Reimupowers/" + NAME + "_128.png"),0,0,128,128);
        if (up){
            upnub++;
            ReimuMod.logger.info("梦想封印 神:打出了升过级的神。 每回合获得升过级的:"+upnub+"张");
        }
        this.particleTimer2 = 2F ;
    }
    public void updateParticles() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer2 -= Gdx.graphics.getDeltaTime();
            // Gdx.graphics.getDeltaTime();
            if(this.particleTimer2 < 0){
                this.particleTimer2 = 2F;
                if (MathUtils.randomBoolean()) { AbstractDungeon.effectsQueue.add(new ConeEffect()); }
                AbstractDungeon.effectsQueue.add(new ConeEffect());
            }
        }
    }
    //描述文本
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void atStartOfTurn() {
        int u = upnub;
        ReimuMod.logger.info("梦想封印 神:回合开始，预计获得"+u+"张升过级的，"+(this.amount-u)+"张没升级的。");
        for (int i = this.amount;i>0;i--){
            AbstractCard c = new Sakura().makeCopy();
            if (u>0){
                u--;
                c.upgrade();
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1, false));
        }
    }

}
