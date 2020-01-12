package ReimuMod.relics.MINE;

import ReimuMod.action.MINE.MoneyEffect;
import ReimuMod.cards.Sign;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class YidongShenshe extends CustomRelic {
    private boolean RclickStart;
    private boolean Rclick;
    private boolean dCheck;
    public static final String NAME = "YidongShenshe";
    private long lastClick;
    private static final int DURATION = 0;	// 双击间隔，间隔时间内没有第二次右键，判断为单击
    private final static AbstractCard c = new Sign(-1,-1) ;
    private final static int cost = 1 ;
    private final static int time = 3 ;
    private final static int nub = 1 ;
    public YidongShenshe() {
        super(
                NAME+":ReiMu",
                ImageMaster.loadImage("img/relics/YidongShenshe.png"),
                ImageMaster.loadImage("img/relics/outline/YidongShenshe.png"),
                RelicTier.SPECIAL,
                LandingSound.SOLID
        );
        this.counter = time;
    }

    public String getUpdatedDescription() {
        return  (this.DESCRIPTIONS[0]+cost+this.DESCRIPTIONS[1]+nub+this.DESCRIPTIONS[2]+time+this.DESCRIPTIONS[3]);
    }

    public AbstractRelic makeCopy() {
        return new YidongShenshe();
    }
    public void clear2(){
        if (this.counter != time ){
            this.counter = time ;
        }
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
    public void atPreBattle() {
        this.tips.clear();
        this.description = (this.DESCRIPTIONS[0]+cost+this.DESCRIPTIONS[1]+nub+this.DESCRIPTIONS[2]+time+this.DESCRIPTIONS[3]+this.DESCRIPTIONS[4]+this.counter+this.DESCRIPTIONS[3]);
        this.clear2();
    }
    public void onVictory() {
        this.tips.clear();
        this.description = getUpdatedDescription();
        this.clear2();
    }
    public void justEnteredRoom(AbstractRoom room){
        this.tips.clear();
        this.description = getUpdatedDescription();
        this.clear2();
    }
    protected void onRightClick(){
        if(this.counter >= 1 && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT ){
            AbstractPlayer p = AbstractDungeon.player;
            if (p.gold>=cost){
                this.counter--;
                this.tips.clear();
                this.description = (this.DESCRIPTIONS[0]+cost+this.DESCRIPTIONS[1]+nub+this.DESCRIPTIONS[2]+time+this.DESCRIPTIONS[3]+this.DESCRIPTIONS[4]+this.counter+this.DESCRIPTIONS[3]);
                this.tips.add(new PowerTip(this.name, this.description));
                this.initializeTips();
                this.flash();
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.player.loseGold(cost);
                for ( int x = cost ; x > 0 ; x -- ){
                            AbstractDungeon.effectList.add(
                                    new MoneyEffect(
                                            AbstractDungeon.player,
                                            -1
                                    )
                            );
                }
                for ( int x = nub ; x > 0 ; x -- ){
                    int s2 = AbstractDungeon.cardRng.random(3);
                    int s1 = AbstractDungeon.cardRng.random(3)+1;
                    AbstractCard ct = new Sign(s1,s2);
                    AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(ct, 1));
                }
            }else {
                c.cantUseMessage = this.DESCRIPTIONS[3];
            }
        }
    }

    protected void onDoubleRightClick() {
        /*
        AbstractPlayer p = AbstractDungeon.player;
        if (p.gold>9){
            for (int i = 10; i > 0; i--) {
                AbstractDungeon.effectList.add(
                        new MoneyEffect(
                                p,
                                p.hb.cX,
                                p.hb.cY,
                                p.hb.cX,
                                p.hb.cY,
                                true,-1
                        )
                );
            }
            int s1 = AbstractDungeon.cardRng.random(3)+1;
            AbstractCard c = new Sign(s1,3);
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c, 1));
        }else {
           c.cantUseMessage = this.DESCRIPTIONS[3];
        }

         */
    }

    private long deltaTime() {
        return System.currentTimeMillis() - this.lastClick;
    }

    private boolean doubleClick() {
        boolean b = this.deltaTime() < DURATION;
        this.lastClick = System.currentTimeMillis();
        return b;
    }

    @Override
    public void update() {
        super.update();
        if (this.RclickStart && InputHelper.justReleasedClickRight) {
            if (this.hb.hovered) {
                this.Rclick = true;
            }
            this.RclickStart = false;
        }
        if ((this.isObtained) && (this.hb != null) && ((this.hb.hovered) && (InputHelper.justClickedRight))) {
            this.RclickStart = true;
        }
        if (this.deltaTime() >= DURATION && this.dCheck) {
            this.dCheck = false;
            this.onRightClick();
        }
        if ((this.Rclick)) {
            this.Rclick = false;
            this.dCheck = true;
            if (this.doubleClick()) {
                this.onDoubleRightClick();
            }
        }
    }

}
