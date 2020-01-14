package ReimuMod.cards.Linmeng.New;

import ReimuMod.action.MINE.setKami;
import ReimuMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

import java.util.ArrayList;

public class KamiSacrifice extends CustomCard {
    public static final String ID = "KamiSacrifice";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID+":ReiMu");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Reimucards/"+ID+".png";
    private static final int COST = 1;
    public KamiSacrifice() {
        super(
                ID+":ReiMu",
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.REIMU_COLOR,
                CardRarity.UNCOMMON,
                AbstractCard.CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = 12 ;
        //this.exhaust = true;
        //this.baseBlock = 4;
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        int q = setKami.getKami2.getKami2("shen");
        if (setKami.getKami2.getKami2("yan") >= setKami.max()){
            q--;
        }
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        if ( q < 1 ) {
            this.cantUseMessage = cardStrings.UPGRADE_DESCRIPTION;
            return false;
        }
        return true;
    }



    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(4);
            //this.upgradeBaseCost(0);
            //this.upgradeBlock(2);
            initializeDescription();
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*
        String[] kami = {"破", "月", "风", "雷", "智", "炎", "阳"};
        int q = 0 ;
        int v = 0 ;
         */
        ArrayList <String> kak = new ArrayList();
        //int q = j.getKami2("shen");
        for(int x = 1;x<7;x++){
            if( setKami.getKami2.getKami2(setKami.kami[x])>=setKami.max()){
                kak.add(setKami.kami[x]);
            }
        }
        if (!Settings.DISABLE_EFFECTS) {
            this.addToBot(new VFXAction(new BorderFlashEffect(Color.RED, true)));
        }
        new setKami(-999,kak.get(AbstractDungeon.cardRng.random(kak.size()-1)));
        this.addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        //this.addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        new setKami(this.magicNumber,"yan");
/*
        if (j.getKami2("yan")>=setKami.max()){q--;}
        int z = 0 ;
        for(AbstractOrb o : p.orbs) {
            if (!(o instanceof EmptyOrbSlot)) {
                z++;
            }
        }

        q=Math.min(q,1);
        while (q>0&&z>0) {
            if (AbstractDungeon.player.orbs.get(0) instanceof KamiPoOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiZhiOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiYueOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiLeiOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiFengOrb || AbstractDungeon.player.orbs.get(0) instanceof KamiYangOrb) {
                ReimuMod.logger.info("神祭,去除:"+p.orbs.get(0).ID);
                switch (p.orbs.get(0).ID) {
                    case KamiPoOrb.ORB_ID: {
                        new setKami(-999, "po");
                        break;
                    }
                    case KamiZhiOrb.ORB_ID: {
                        new setKami(-999, "zhi");
                        break;
                    }
                    case KamiYueOrb.ORB_ID: {
                        new setKami(-999, "yue");
                        break;
                    }
                    case KamiLeiOrb.ORB_ID: {
                        new setKami(-999, "lei");
                        break;
                    }
                    case KamiFengOrb.ORB_ID: {
                        new setKami(-999, "feng");
                        break;
                    }
                    case KamiYangOrb.ORB_ID: {
                        new setKami(-999, "yang");
                        break;
                    }
                }
                AbstractDungeon.player.removeNextOrb();
                new setKami(this.magicNumber,"yan");
                q--;
                z--;
            } else {
                for (int i = 1 ; i != z ;i++){
                    Collections.swap(AbstractDungeon.player.orbs, i, i-1);
                }
            }
        }

         */
        /*
        setKami.getKami2 x = new setKami.getKami2();
        if (x.getKami2("yan") >= setKami.max()){
            q++;
        }
        v = x.getKami2("shen") - q;
        if (v > 0){
            ArrayList<String> list1 = new ArrayList<>();
            for(String s : kami){
                if (s != "炎" &&x.getKami2(s)>=setKami.max()){
                    list1.add(s);
                }
            }
            new setKami(-999,list1.get(AbstractDungeon.cardRng.random(list1.size()-1)));
            new setKami(this.magicNumber,"yan");
        }
         */
    }
/*
    private void change(AbstractPlayer p,int q) {
        switch (p.orbs.get(0).ID){
            case KamiPoOrb.ORB_ID:{ new setKami(-999,"po");break; }
            case KamiZhiOrb.ORB_ID:{ new setKami(-999,"zhi");break; }
            case KamiYueOrb.ORB_ID:{ new setKami(-999,"yue");break; }
            case KamiLeiOrb.ORB_ID:{ new setKami(-999,"lei");break; }
            case KamiFengOrb.ORB_ID:{ new setKami(-999,"feng");break; }
            case KamiYangOrb.ORB_ID:{ new setKami(-999,"yang");break; }
            default:{
                if (this.n<20){
                    this.n++;
                    ReimuMod.logger.info("神献：目标错误,再次尝试");
                    for (int i = 1 ; i != q + 1 ;i++){
                        Collections.swap(p.orbs, i, i-1);
                    }
                    change(p,q);
                    break;
                }else {
                    ReimuMod.logger.info("神献：多次失败，放弃执行");
                    this.n = 0;
                    this.kami = false ;
                    break;
                }
            }
        }
    }
 */
}
