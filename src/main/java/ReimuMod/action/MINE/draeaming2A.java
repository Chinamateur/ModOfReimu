package ReimuMod.action.MINE;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import org.graalvm.compiler.virtual.phases.ea.EffectList;

public class draeaming2A extends AbstractGameAction {
    private AbstractCard card;
    private AttackEffect effect;
    public draeaming2A(AbstractCard card, AttackEffect effect) {
        this.card = card;
        this.effect = effect;
    }

    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.card.calculateCardDamage((AbstractMonster)this.target);
            if (AttackEffect.LIGHTNING == this.effect) {
                this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.NONE));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(this.target.hb.cX, this.target.hb.cY)));
            } else {
                this.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), this.effect));
                this.addToTop(new VFXAction(new tenlighetEffect(AbstractDungeon.player.hb.cX,AbstractDungeon.player.hb.cY,this.target.hb.cX,this.target.hb.cY, Color.WHITE)));
            }
        }

        this.isDone = true;
    }
}
