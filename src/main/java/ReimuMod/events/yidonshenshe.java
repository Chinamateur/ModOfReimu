package ReimuMod.events;

import ReimuMod.ReimuMod;
import ReimuMod.cards.Poor;
import ReimuMod.characters.ReiMu;
import ReimuMod.relics.MINE.PaintedHorse;
import ReimuMod.relics.MINE.SkyOrb;
import ReimuMod.relics.MINE.YidongShenshe;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.curses.Decay;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class yidonshenshe extends AbstractImageEvent {
    public static final String ID = "yidonshenshe";
    private static final EventStrings PPP = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DES = PPP.DESCRIPTIONS;
    public static final String[] OP = PPP.OPTIONS;
    private static final int cost = 30 + ((AbstractDungeon.ascensionLevel >= 15)? 30:10);
    private AbstractCard card;
    public yidonshenshe() {
        super(PPP.NAME, DES[0], null);
        this.card = new Poor().makeCopy();
        if (AbstractDungeon.player.gold >= cost ) {
            this.imageEventText.setDialogOption(OP[2]+cost+OP[3],AbstractDungeon.player.gold < cost,new PaintedHorse());
        } else {
            this.imageEventText.setDialogOption(OP[0]+cost+OP[1],AbstractDungeon.player.gold < cost,new PaintedHorse());
        }

        //if (AbstractDungeon.ascensionLevel >= 15) {
        this.imageEventText.setDialogOption(OP[4],card);
        //}else { this.imageEventText.setDialogOption(OPTIONS[5]); }
        this.imageEventText.setDialogOption(OP[5]);


        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.EVENT;
        this.hasDialog = true;
        this.hasFocus = true;
    }

    protected void buttonEffect(int buttonPressed) {
        AbstractRelic relic;
        ReiMu.logger.info((AbstractDungeon.player instanceof ReiMu));
        switch(this.screenNum) {
            case 0:
                switch(buttonPressed) {
                    case 0:
                        if(AbstractDungeon.player.gold>=cost){
                            if (!AbstractDungeon.player.hasRelic("PaintedHorse:ReiMu")) {
                                relic = new PaintedHorse();
                            } else {
                                relic = new Circlet();
                            }
                            this.imageEventText.updateBodyText(DES[1]);
                            AbstractEvent.logMetricObtainRelicAtCost(ID, OP[6], relic, cost);
                            AbstractDungeon.player.loseGold(cost);
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, relic);
                        }

                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DES[2]);
                        if (!AbstractDungeon.player.hasRelic("YidongShenshe:ReiMu")) {
                            relic = new YidongShenshe();
                        } else {
                            relic = new Circlet();
                        }
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, relic);
                        AbstractEvent.logMetricObtainCardAndRelic(ID, OP[7], card,relic);
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));

                        break;
                    default:
                        this.imageEventText.updateBodyText(DES[3]);
                        //this.openMap();
                }
                this.screenNum = 1;
                this.imageEventText.updateDialogOption(0, OP[5]);
                this.imageEventText.clearRemainingOptions();
                break;
            default:
                this.openMap();
        }
    }

}

