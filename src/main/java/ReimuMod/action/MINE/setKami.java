package ReimuMod.action.MINE;

import ReimuMod.ReimuMod;
import ReimuMod.cards.Dreaming2;
import ReimuMod.powers.Kami.Effect.MylightEffect;
import ReimuMod.powers.Kami.Effect.broEffect;
import ReimuMod.powers.Kami.KamiFengPower;
import ReimuMod.powers.Kami.KamiLeiPower;
import ReimuMod.powers.Kami.KamiPoPower;
import ReimuMod.powers.Kami.KamiYanPower;
import ReimuMod.powers.Kami.KamiYangPower;
import ReimuMod.powers.Kami.KamiYuePower;
import ReimuMod.powers.Kami.KamiZhiPower;
import ReimuMod.relics.MINE.CrownOfHeavens;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.*;

import java.util.ArrayList;

public class setKami {
    //private static AbstractPlayer p = AbstractDungeon.player;
    public static final String[] kami = {"炎","破","雷","智","月","风","阳"};
    public static final String[] kami2 = {"yan","po","lei","zhi","yue","feng","yang"};
    public static final String[] kami3 = {"Flame","Break","Thunderbolt","Wisdom","Moon","Wind","Sun"};
    private static final int baseMax = 10 ;
    public setKami(int amt, String power) {
        if((amt<0||(amt>0 && getKami(power)<max())|| power.equals("yan") || power.equals("炎"))&&(amt != 0)){
            if((amt>0)&& (AbstractDungeon.player.hasPower("YinYangSangePower:ReiMu")&&AbstractDungeon.player.getPower("YinYangSangePower:ReiMu").amount>0)){
                ReimuMod.logger.info("神信堆叠:阴阳散华触发。");
                ArrayList<String> list1 = new ArrayList<>();
                getKami2 x = new getKami2();
                int hh = 0 ;
                for (String i : kami) {
                    if ( (x.getKami2(i) < max()) && !kami[hh].equals(power) && !kami2[hh].equals(power)
                           // !( x.getKami2(power)+amt >= max() && (kami[hh].equals(power) || kami2[hh].equals(power)))
                    ) {
                        list1.add(i);
                    }
                    hh ++ ;
                }
                if (list1.size()>0){
                    AbstractDungeon.player.getPower("YinYangSangePower:ReiMu").flash();
                    AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
                    int z = AbstractDungeon.cardRng.random(list1.size()-1);
                    setnumber(AbstractDungeon.player.getPower("YinYangSangePower:ReiMu").amount, list1.get(z));
                }
                //AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
            }
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
            setnumber(amt,power);
        }
    }
    public static int max(){
        int x = baseMax;
        if (AbstractDungeon.player.hasRelic("DonationBox:ReiMu")){
            x+=3 ;
        }
        return x;
    }


    private void setnumber(int amt, String power){
        switch (power) {
            case "po":
            case "破":
                ReimuMod.logger.info("神信堆叠action：预计堆叠" + amt + "层" + power);
                if (amt > 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamiPoPower(AbstractDungeon.player, amt), amt)
                    );
                }else if (amt == -999) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "KamiPoPower:ReiMu"));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,"KamiPoPower:ReiMu", -amt));
                }
                break;
            case "yue":
            case "月":
                ReimuMod.logger.info("神信堆叠action：预计堆叠" + amt + "层" + power);
                if (amt > 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamiYuePower(AbstractDungeon.player, amt), amt)
                    );
                }else if (amt == -999) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "KamiYuePower:ReiMu"));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,"KamiYuePower:ReiMu", -amt));
                }
                break;
            case "feng":
            case "风":
                ReimuMod.logger.info("神信堆叠action：预计堆叠" + amt + "层" + power);
                if (amt > 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamiFengPower(AbstractDungeon.player, amt), amt)
                    );
                }else if (amt == -999) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "KamiFengPower:ReiMu"));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,"KamiFengPower:ReiMu", -amt));
            }
                break;
            case "lei":
            case "雷":
                ReimuMod.logger.info("神信堆叠action：预计堆叠" + amt + "层" + power);
                if (amt > 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamiLeiPower(AbstractDungeon.player, amt), amt)
                    );
                }else if (amt == -999) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "KamiLeiPower:ReiMu"));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,"KamiLeiPower:ReiMu", -amt));
            }
                break;
            case "zhi":
            case "智":
                ReimuMod.logger.info("神信堆叠action：预计堆叠" + amt + "层" + power);
                if (amt > 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamiZhiPower(AbstractDungeon.player, amt), amt)
                    );
                }else if (amt == -999) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "KamiZhiPower:ReiMu"));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,"KamiZhiPower:ReiMu", -amt));
            }
                break;
            case "yan":
            case "炎":
                ReimuMod.logger.info("神信堆叠action：预计堆叠" + amt + "层" + power);
                if (amt > 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamiYanPower(AbstractDungeon.player, amt), amt)
                    );
                }else if (amt == -999) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "KamiYanPower:ReiMu"));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,"KamiYanPower:ReiMu", -amt));
            }
                break;
            case "yang":
            case "阳":
                ReimuMod.logger.info("神信堆叠action：预计堆叠" + amt + "层" + power);
                if (amt > 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamiYangPower(AbstractDungeon.player, amt), amt)
                    );
                }else if (amt == -999) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "KamiYangPower:ReiMu"));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,"KamiYangPower:ReiMu", -amt));
            }
                break;
            case "all":
            case "全":
                ReimuMod.logger.info("神信堆叠action：预计堆叠" + amt + "层" + power);
                for (String i : kami) {
                    setnumber(amt, i);
                }
                break;
            case "big":
            case "多": {
                ArrayList<String> list1 = new ArrayList<>();
                int x = 0;
                for (String n : kami) {
                    if (getKami(n) > x && (n.equals("炎") || getKami(n) < max())) {
                        list1.clear();
                        list1.add(n);
                        x = getKami(n);
                    } else if (getKami(n) == x && (n.equals("炎") || getKami(n) < max())) {
                        list1.add(n);
                    }
                }
                if (list1.size() > 1) {
                    setnumber(amt, list1.get(AbstractDungeon.cardRng.random( list1.size()-1)));
                } else if (list1.size() == 1) {
                    setnumber(amt, list1.get(0));
                } else {
                    setnumber(amt, "roll");
                }
                break;
            }
            case "low":
            case "少": {
                ArrayList<String> list1 = new ArrayList<>();
                int x = max();
                for (String n : kami) {
                    if (getKami(n) < x) {
                        list1.clear();
                        list1.add(n);
                        x = getKami(n);
                    } else if (getKami(n) == x && getKami(n) != max()) {
                        list1.add(n);
                    }
                }
                if (list1.size() > 1) {
                    setnumber(amt, list1.get(AbstractDungeon.cardRng.random(list1.size()-1)));
                } else if (list1.size() == 1) {
                    setnumber(amt, list1.get(0));
                } else {
                    setnumber(amt, "yan");
                }
                break;
            }
            default:
                ReimuMod.logger.info("神信堆叠action：输入了非预期的堆叠对象！随机给予");
                setnumber(amt, rolKami());
                break;
        }
    }

    public int getKami(String power) {
        switch (power) {
            case "po":
            case "破":
                return AbstractDungeon.player.hasPower("KamiPoPower:ReiMu") ? AbstractDungeon.player.getPower("KamiPoPower:ReiMu").amount : 0;
            case "yue":
            case "月":
                return AbstractDungeon.player.hasPower("KamiYuePower:ReiMu") ? AbstractDungeon.player.getPower("KamiYuePower:ReiMu").amount : 0;
            case "feng":
            case "风":
                return AbstractDungeon.player.hasPower("KamiFengPower:ReiMu") ? AbstractDungeon.player.getPower("KamiFengPower:ReiMu").amount : 0;
            case "lei":
            case "雷":
                return AbstractDungeon.player.hasPower("KamiLeiPower:ReiMu") ? AbstractDungeon.player.getPower("KamiLeiPower:ReiMu").amount : 0;
            case "zhi":
            case "智":
                return AbstractDungeon.player.hasPower("KamiZhiPower:ReiMu") ? AbstractDungeon.player.getPower("KamiZhiPower:ReiMu").amount : 0;
            case "yan":
            case "炎":
                return AbstractDungeon.player.hasPower("KamiYanPower:ReiMu") ? AbstractDungeon.player.getPower("KamiYanPower:ReiMu").amount : 0;
            case "yang":
            case "阳":
                return AbstractDungeon.player.hasPower("KamiYangPower:ReiMu") ? AbstractDungeon.player.getPower("KamiYangPower:ReiMu").amount : 0;
            case "all":
            case "全":
                int x = 0;
                for (String i : kami) {
                    x += getKami(i);
                }
                return x;
            default:
                ReimuMod.logger.info("神信获取action：输入了非预期的字符串！");
                return 0;
        }
    }

    private String rolKami() {
        ArrayList<String> list1 = new ArrayList<>();
        for (String i : kami) {
            if (i.equals("炎") || getKami(i) < max()) {
                list1.add(i);
            }
        }
        int x = AbstractDungeon.cardRng.random(list1.size()-1);
        return list1.get(x);
    }

    public static class Kami10 {
        public Kami10(int x) {
            AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(AbstractDungeon.player));
            if(AbstractDungeon.player.hasRelic("CrownOfHeavens:ReiMu")) {
                AbstractDungeon.player.getRelic("CrownOfHeavens:ReiMu").flash();
                AbstractDungeon.actionManager.addToBottom((new RelicAboveCreatureAction(AbstractDungeon.player, AbstractDungeon.player.getRelic("CrownOfHeavens:ReiMu"))));
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(CrownOfHeavens.s1, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            }
            if(AbstractDungeon.player.hasRelic("KaguraBell:ReiMu")){
                AbstractDungeon.player.getRelic("KaguraBell:ReiMu").flash();
                AbstractDungeon.actionManager.addToBottom((new RelicAboveCreatureAction(AbstractDungeon.player, AbstractDungeon.player.getRelic("KaguraBell:ReiMu"))));
                AbstractDungeon.actionManager.addToBottom((new GainEnergyAction(1)));
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
            }
            ArrayList<AbstractCard> list = new ArrayList();
            for(AbstractCard c :AbstractDungeon.player.hand.group){
                if (c instanceof Dreaming2){
                    list.add(c);
                }
            }
            if (list.size()>0){
                for(AbstractCard b : list){
                    b.baseHeal = b.heal = x;
                    b.applyPowers();
                    b.flash();
                }
            }
            AbstractOrb o = null;

            float MyY = setSob(x,0);
            float MyX = setSob(x,1);
            //AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(MyX, MyY,KamiColoer(x))));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new MyOrbEffect(MyX, MyY,KamiColoer(x),true,x)));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(KamiColoer(x), true)));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new FrostOrbPassiveEffect(MyX, MyY)));

        }
        public static float setSob(int slotNum,int x) {
            float dist = 100.0F * Settings.scale + 7.0F * 10.0F * Settings.scale;
            float angle = 100.0F + 7.0F * 12.0F;
            float offsetAngle = angle / 2.0F;
            angle *= (float)slotNum / (7.0F - 1.0F);
            angle += 90.0F - offsetAngle;
            float tX = dist * MathUtils.cosDeg(angle) + AbstractDungeon.player.drawX;
            float tY = dist * MathUtils.sinDeg(angle) + AbstractDungeon.player.drawY + AbstractDungeon.player.hb_h / 2.0F;
            return x>0? tX:tY;
        }
        public static Color KamiColoer(int i){
            switch (i){
                case 1:{
                    return new Color(0.9F,0.0F,0.8F,1F).cpy();
                }
                case 2:{
                    return new Color(0.15F,0.0F,0.9F,1F).cpy();
                }
                case 3:{
                    return new Color(0F,0.65F,0.9F,1F).cpy();
                }
                case 4:{
                    return  new Color(0F,0.9F,0.45F,1F).cpy();
                }
                case 5:{
                    return new Color(0.45F,0.9F,0.0F,1F).cpy();
                }
                case 6:{
                    return new Color(0.9F,0.75F,0.0F,1F).cpy();
                }
                default:{
                    return new Color(0.9F,0.0F,0.0F,1F).cpy();
                }
            }
        }
        public static Color KamiColoer2(int i){
            switch (i){
                case 1:{
                    return new Color(1F,0.0F,0.85F,1F).cpy();
                }
                case 2:{
                    return new Color(0.2F,0.0F,1F,1F).cpy();
                }
                case 3:{
                    return new Color(0F,7.5F,1F,1F).cpy();
                }
                case 4:{
                    return  new Color(0F,1F,0.55F,1F).cpy();
                }
                case 5:{
                    return new Color(0.55F,1F,0.0F,1F).cpy();
                }
                case 6:{
                    return new Color(1F,0.8F,0.0F,1F).cpy();
                }
                default:{
                    return new Color(1F,0.0F,0.0F,1F).cpy();
                }
            }
        }


    }

    public static class getKami2 {
        private static  String[] kami = {"破", "月", "风", "雷", "智", "炎", "阳"};


        public static AbstractPower getAbKami(String power){
        switch (power) {
            case "po":
            case "破":
                return AbstractDungeon.player.hasPower("KamiPoPower:ReiMu") ? AbstractDungeon.player.getPower("KamiPoPower:ReiMu") : null;
            case "yue":
            case "月":
                return AbstractDungeon.player.hasPower("KamiYuePower:ReiMu") ? AbstractDungeon.player.getPower("KamiYuePower:ReiMu") : null;
            case "feng":
            case "风":
                return AbstractDungeon.player.hasPower("KamiFengPower:ReiMu") ? AbstractDungeon.player.getPower("KamiFengPower:ReiMu") : null;
            case "lei":
            case "雷":
                return AbstractDungeon.player.hasPower("KamiLeiPower:ReiMu") ? AbstractDungeon.player.getPower("KamiLeiPower:ReiMu") : null;
            case "zhi":
            case "智":
                return AbstractDungeon.player.hasPower("KamiZhiPower:ReiMu") ? AbstractDungeon.player.getPower("KamiZhiPower:ReiMu") : null;
            case "yan":
            case "炎":
                return AbstractDungeon.player.hasPower("KamiYanPower:ReiMu") ? AbstractDungeon.player.getPower("KamiYanPower:ReiMu") : null;
            case "yang":
            case "阳":
                return AbstractDungeon.player.hasPower("KamiYangPower:ReiMu") ? AbstractDungeon.player.getPower("KamiYangPower:ReiMu") : null;
            default:
                return null;
        }
    }
        public static int getKami2(String power) {
            if (power.equals("po") || power.equals("破")) {
                return AbstractDungeon.player.hasPower("KamiPoPower:ReiMu") ? AbstractDungeon.player.getPower("KamiPoPower:ReiMu").amount : 0;
            } else if (power.equals("yue") || power.equals("月")) {
                return AbstractDungeon.player.hasPower("KamiYuePower:ReiMu") ? AbstractDungeon.player.getPower("KamiYuePower:ReiMu").amount : 0;
            } else if (power.equals("feng") || power.equals("风")) {
                return AbstractDungeon.player.hasPower("KamiFengPower:ReiMu") ? AbstractDungeon.player.getPower("KamiFengPower:ReiMu").amount : 0;
            } else if (power.equals("lei") || power.equals("雷")) {
                return AbstractDungeon.player.hasPower("KamiLeiPower:ReiMu") ? AbstractDungeon.player.getPower("KamiLeiPower:ReiMu").amount : 0;
            } else if (power.equals("zhi") || power.equals("智")) {
                return AbstractDungeon.player.hasPower("KamiZhiPower:ReiMu") ? AbstractDungeon.player.getPower("KamiZhiPower:ReiMu").amount : 0;
            } else if (power.equals("yan") || power.equals("炎")) {
                return AbstractDungeon.player.hasPower("KamiYanPower:ReiMu") ? AbstractDungeon.player.getPower("KamiYanPower:ReiMu").amount : 0;
            } else if (power.equals("yang") || power.equals("阳")) {
                return AbstractDungeon.player.hasPower("KamiYangPower:ReiMu") ? AbstractDungeon.player.getPower("KamiYangPower:ReiMu").amount : 0;
            } else if (power.equals("all") || power.equals("全")) {
                int x = 0;
                for (String i : kami) {
                    x += getKami2(i);
                }
                return x;
            } else if (power.equals("shen") || power.equals("神")) {
                int x = 0;
                for (String i : kami) {
                    if (getKami2(i) >= setKami.max() ){
                        x ++;
                    }
                }
                ReimuMod.logger.info("神信获取action：神的数量为:"+x);
                return x;
            } else if (power.equals("xin") || power.equals("quan")) {
                int x = 0;
                for (String i : kami) {
                    if (getKami2(i) >= setKami.max() ){
                        x ++;
                    }
                }
                ReimuMod.logger.info("神信获取action：神的数量为:"+x);
                return x;
            } else {
                ReimuMod.logger.info("神信获取action：输入了非预期的字符串！");
                return 0;
            }

        }

    }
}

