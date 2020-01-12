package ReimuMod.relics.MINE;

import ReimuMod.ReimuMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

public class RuthlessExorcism extends CustomRelic {
    public AbstractRelic makeCopy() { return new RuthlessExorcism(); }
    public static final String NAME = "RuthlessExorcism";
    public RuthlessExorcism() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/"+NAME+".png"),
                ImageMaster.loadImage("img/relics/outline/"+NAME+".png"),
                RelicTier.COMMON,
                LandingSound.SOLID
        );
        this.counter=6;
        getUpdatedDescription();
    }

    public void  atBattleStart() {
        this.flash();
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss || AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
            this.counter+=1;
            getUpdatedDescription();
        }
        ReimuMod.logger.info("遗物:无情的驱魔棒生效");
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.counter, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+6+DESCRIPTIONS[1]+1+DESCRIPTIONS[2];
    }
}
