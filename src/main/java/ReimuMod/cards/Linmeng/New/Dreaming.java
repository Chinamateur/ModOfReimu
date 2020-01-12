package ReimuMod.cards.Linmeng.New;

import ReimuMod.ReimuMod;
import ReimuMod.action.MINE.MyOrbEffect;
import ReimuMod.action.MINE.setKami;
import ReimuMod.action.MINE.tenlighetEffect;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class Dreaming extends CustomCard {
    private static final setKami.getKami2 j = new setKami.getKami2();
    public static final String ID = "Dreaming";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    private static final int COST = 3;
    public Dreaming() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION_UPG,
                CardType.ATTACK,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.baseDamage = 10;
        this.isMultiDamage = true;
        //this.baseMagicNumber= this.magicNumber = 9;
        //this.isEthereal = true;
        //this.exhaust = true ;
        //this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (j.getKami2("shen")>0&& ReimuMod.MyReimuModbol){
            p.state.setAnimation(0, "Dream", false);
            p.state.addAnimation(0, "newAnimation", true,0.0F);
            AbstractDungeon.actionManager.addToBottom(new WaitAction(1));
        }
        int i = 0 ;
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);
        //AbstractDungeon.actionManager.addToBottom(new VFXAction(new tenlighetEffect(setKami.Kami10.setSob(i,1),setKami.Kami10.setSob(i,0),randomMonster.hb.cX,randomMonster.hb.cY,Color.WHITE),0.4F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new  MyOrbEffect(randomMonster.hb.cX, randomMonster.hb.cY, Color.WHITE)));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT,j.getKami2("shen")>2));

        for(String s : setKami.kami){
            if (j.getKami2(s)>=setKami.max()){
                nn(i);
            }
            i++;
        }

    }
    public void nn(int i){
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(true);
        if (randomMonster!= null){
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(setKami.Kami10.KamiColoer(i), true)));
            //AbstractDungeon.actionManager.addToBottom(new VFXAction(new tenlighetEffect(setKami.Kami10.setSob(i,1),setKami.Kami10.setSob(i,0),randomMonster.hb.cX,randomMonster.hb.cY,setKami.Kami10.KamiColoer(i)),0.4F));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new MyOrbEffect(randomMonster.hb.cX, randomMonster.hb.cY, setKami.Kami10.setSob(i,1),setKami.Kami10.setSob(i,0), setKami.Kami10.KamiColoer(i)), 0F));
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT,j.getKami2("shen")>2));
        }
    }
 /*
        int x = j.getKami2("shen")+1;
        if(x>0){

            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.2F*x));

            for (int z = x ;z>0;z--){
                AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                if (randomMonster != null) {

                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE,x>2));
            }
        }
        //AbstractDungeon.actionManager.addToTop(new EvokeAllOrbsAction());
           */

    /*
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if (j.getKami2("shen") == 0)
        {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

     */


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(4);
            //this.upgradeMagicNumber(1);
            //this.upgradeBaseCost(1);
            //this.isEthereal = false;
            //this.upgradeBaseCost(1);
            //this.isInnate =true;
            //this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
