package ReimuMod.relics.MINE;

import ReimuMod.powers.SealPower;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TentaclePot extends CustomRelic {
    private  static  final int zz = 4;
    public TentaclePot() {
        super(
                "TentaclePot:ReiMu",
                ImageMaster.loadImage("img/relics/TentaclePot.png"),
                ImageMaster.loadImage("img/relics/outline/TentaclePot.png"),
                RelicTier.SHOP,
                LandingSound.SOLID
        );
    }

    public void  atBattleStart() {
        this.flash();
        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,AbstractDungeon.player,new ConstrictedPower(m,AbstractDungeon.player,zz),zz));
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+zz+DESCRIPTIONS[1];
    }
    public AbstractRelic makeCopy() { return new TentaclePot(); }
}
