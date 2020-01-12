package ReimuMod.events;

import ReimuMod.ReimuMod;
import ReimuMod.cards.Linmeng.New.ZunPunch;
import ReimuMod.characters.ReiMu;
import ReimuMod.relics.MINE.ZunR;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZunEvent extends AbstractImageEvent {
    private int x ;
    public static final String ID = "ZunEvent";
    private static final EventStrings PPP = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DES = PPP.DESCRIPTIONS;
    public static final String[] OP = PPP.OPTIONS;
    private AbstractRelic relic ;
    private static final int cost = 40 + ((AbstractDungeon.ascensionLevel >= 15)? 40:0);
    private AbstractCard card;
    public ZunEvent() {
        super(PPP.NAME, DES[0], null);
        relic = new ZunR().makeCopy();
        card = new ZunPunch().makeCopy();
        if (AbstractDungeon.player.gold >= cost ) {
            this.imageEventText.setDialogOption(OP[2]+cost+OP[3],AbstractDungeon.player.gold < cost,relic);
        } else {
            this.imageEventText.setDialogOption(OP[0]+cost+OP[1],AbstractDungeon.player.gold < cost,relic);
        }

        this.imageEventText.setDialogOption(OP[5]);

        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.EVENT;
        this.hasDialog = true;
        this.hasFocus = true;
    }

    protected void buttonEffect(int buttonPressed) {
        ReiMu.logger.info((AbstractDungeon.player instanceof ReiMu));
        switch(this.screenNum) {
            case 0:
                this.screenNum ++;
                switch(buttonPressed) {
                    case 0:
                        if(AbstractDungeon.player.gold>=cost){
                            AbstractRelic aa;
                            if (AbstractDungeon.player.hasRelic(relic.relicId)) {
                                aa = new Circlet();
                            }else {
                                aa = relic.makeCopy();
                            }
                            this.imageEventText.updateBodyText(DES[1]);
                            AbstractDungeon.player.loseGold(cost);
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, aa);
                            this.imageEventText.updateDialogOption(0, OP[8]);
                        }
                        break;
                    default:
                        this.screenNum = 7;
                        this.imageEventText.updateBodyText(DES[2]);
                        this.imageEventText.updateDialogOption(0, OP[8]);
                        //this.openMap();
                }
                this.imageEventText.clearRemainingOptions();
                break;
            case 1 :
                this.screenNum ++;
                if(!(AbstractDungeon.player instanceof ReiMu)){
                    this.imageEventText.updateBodyText(DES[3]);
                    card = new ZunPunch(2);
                }else {
                    this.imageEventText.updateBodyText(DES[7]);
                    card = new ZunPunch();
                }
                this.imageEventText.updateDialogOption(0, OP[4],card);
                /*
                if (AbstractDungeon.player.hasRelic(relic.relicId)){
                    this.imageEventText.updateDialogOption(0, OP[4],!AbstractDungeon.player.hasRelic(relic.relicId));
                }else {
                    this.imageEventText.updateDialogOption(0, OP[0]+OP[9],!AbstractDungeon.player.hasRelic(relic.relicId));
                }

                 */
                this.imageEventText.clearRemainingOptions();
                if (AbstractDungeon.ascensionLevel >= 15){
                    //this.imageEventText.updateDialogOption(1, OP[6]);
                    x = (int)(AbstractDungeon.player.maxHealth * 0.1F);
                    this.imageEventText.setDialogOption(OP[6]+x+OP[7]);
                }else {
                    //this.imageEventText.updateDialogOption(1, OP[5]);
                    this.imageEventText.setDialogOption(OP[5]);
                }
                break;
            case 2:
                this.screenNum ++;
                switch(buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DES[6]);
                        if (AbstractDungeon.player.hasRelic(relic.relicId)){
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
                            AbstractDungeon.player.loseRelic(relic.relicId);
                            List<String> tempList1 = new ArrayList();
                            List<String> tempList2 = new ArrayList();
                            tempList1.add(card.cardID);
                            tempList2.add(relic.relicId);
                            logMetric(ID, OP[11], tempList1, (List)null, (List)null, (List)null, (List)null, (List)null, tempList2, 0, 0, 0, 0, 0, 0);
                        }
                        break;
                    case 1:
                        if (AbstractDungeon.ascensionLevel >= 15){
                            this.imageEventText.updateBodyText(DES[5]);
                            CardCrawlGame.screenShake.shake(ShakeIntensity.MED, ShakeDur.MED, false);
                            CardCrawlGame.sound.play("BLUNT_FAST");
                            AbstractDungeon.player.damage(new DamageInfo(null,x, DamageInfo.DamageType.HP_LOSS));
                            AbstractEvent.logMetricObtainRelicAndDamage(ID,OP[10],relic,x);
                        }else {
                            AbstractEvent.logMetricObtainRelicAtCost(ID,  OP[10], relic, cost);
                            this.imageEventText.updateBodyText(DES[4]);
                        }
                        break;
                }
                this.imageEventText.updateDialogOption(0, OP[8]);
                this.imageEventText.clearRemainingOptions();
                break;
                /*
            case 7:
                this.screenNum ++;
                this.imageEventText.updateBodyText(DES[2]);
                this.imageEventText.updateDialogOption(0, OP[8]);
                this.imageEventText.clearRemainingOptions();
                break;
                 */
            default:
                this.openMap();
        }
    }

}

