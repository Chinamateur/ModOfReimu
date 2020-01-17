package ReimuMod.characters;

import ReimuMod.ReimuMod;
import ReimuMod.cards.*;
import ReimuMod.cards.Linmeng.New.*;
import ReimuMod.patches.AbstractCardEnum;
import ReimuMod.patches.ReimuClassEnum;
import ReimuMod.powers.Kami.*;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReiMu extends CustomPlayer {

  private static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
  private static final String REIMU_SHOULDER_2 = "img/char/Reimu/shoulder2.png"; // shoulder2 / shoulder_1
  private static final String REIMU_SHOULDER_1 = "img/char/Reimu/shoulder1.png"; // shoulder1 / shoulder_2
  //private static final String REIMU_CORPSE = "img/char/Reimu/fallen.png"; // dead corpse

  public String ENDSCENE1 = "img/scenes/reimu1.png";
  public String ENDSCENE2 = "img/scenes/reimu2.png";
  public String ENDSCENE3 = "img/scenes/reimu3.png";

  public static final Logger logger = LogManager.getLogger(ReimuMod.class.getName());
  private static final float[] LAYER_SPEED =
          {40.0F, 30.0F, -20.0F, 20.0F, 0.0F, 10.0F, 8.0F, -5.0F, 5.0F, 0.0F};
  private static final String REIMU_SKELETON_ATLAS = ReimuMod.MyReimuModbol?"img/char/Reimu/NewProject_5.atlas":"img/char/Reimu/MarisaModelv3.atlas";// Marisa_v0 / MarisaModel_v02 /MarisaModelv3
  private static final String REIMU_SKELETON_JSON = ReimuMod.MyReimuModbol?"img/char/Reimu/NewProject_5_Armatureface_spellCall.json":"img/char/Reimu/MarisaModelv3.json";
  private static final String REIMU_ANIMATION = ReimuMod.MyReimuModbol?"newAnimation":"Idle";// Sprite / Idle
  private static final String[] ORB_TEXTURES = {
      "img/ReimuUI/EPanel/layer5.png",
      "img/ReimuUI/EPanel/layer4.png",
      "img/ReimuUI/EPanel/layer3.png",
      "img/ReimuUI/EPanel/layer2.png",
      "img/ReimuUI/EPanel/layer1.png",
      "img/ReimuUI/EPanel/layer0.png",
      "img/ReimuUI/EPanel/layer5d.png",
      "img/ReimuUI/EPanel/layer4d.png",
      "img/ReimuUI/EPanel/layer3d.png",
      "img/ReimuUI/EPanel/layer2d.png",
      "img/ReimuUI/EPanel/layer1d.png"
  };
  private static final String ORB_VFX = "img/ReimuUI/energyBlueVFX.png";

    //public static final String SPRITER_ANIM_FILEPATH = "img/char/MyCharacter/marisa_test.scml"; // spriter animation scml

  public ReiMu(String name) {
    //super(name, setClass, null, null , null ,new SpriterAnimation(SPRITER_ANIM_FILEPATH));
    super(name, ReimuClassEnum.REIMU, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, null, null);
    //super(name, setClass, null, null, (String) null, null);

    this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
    this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

    logger.info("init 灵梦");

    initializeClass(
            null,
            REIMU_SHOULDER_2, // reqUIred call to load textures and setup energy/loadout
            REIMU_SHOULDER_1,
            ReimuMod.MyReimuModbol?"img/char/Reimu/fallen.png":"img/char/Reimu/fallen2.png",
        getLoadout(),
        0F, -10.0F, 200.0F, 350.0F,
        new EnergyManager(ENERGY_PER_TURN)
    );

    AnimationState.TrackEntry e;
    // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
if(ReimuMod.MyReimuModbol){
  loadAnimation("img/char/Reimu/NewProject_5.atlas","img/char/Reimu/NewProject_5_Armatureface_spellCall.json",2.0F);
  e = this.state.setAnimation(0,"newAnimation", true);
}else {
  loadAnimation("img/char/Reimu/Reimu.atlas", "img/char/Reimu/Reimu.json",3.4F);
  e = this.state.setAnimation(0,"normal", true);
}
    e.setTime(e.getEndTime() * MathUtils.random());
    e.setTimeScale(1.0F);
    logger.info("灵梦 init finish");
  }


  public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
    ArrayList<String> retVal = new ArrayList<>();
/*
    retVal.add(GirlDream.ID);
    retVal.add(GirlDream.ID);
    retVal.add(GirlDream.ID);
    retVal.add(GirlDream.ID);
    retVal.add(GirlDream.ID);

 */

    //retVal.add("NDamage");


    retVal.add("Strike_Lin:ReiMu");
    retVal.add("Strike_Lin:ReiMu");
    retVal.add("Strike_Lin:ReiMu");
    retVal.add("Strike_Lin:ReiMu");

    retVal.add("Defend_Lin:ReiMu");
    retVal.add("Defend_Lin:ReiMu");
    retVal.add("Defend_Lin:ReiMu");
    retVal.add("Defend_Lin:ReiMu");

    retVal.add("NB:ReiMu");
    retVal.add("Stagnation:ReiMu");



    return retVal;
  }

  public ArrayList<String> getStartingRelics() { // starting relics - also simple
    ArrayList<String> retVal = new ArrayList<>();
    retVal.add("FlyPower:ReiMu");
    return retVal;
  }

  private static final int STARTING_HP = 65;
  private static final int MAX_HP = 65;
  private static final int STARTING_GOLD = 99;
  private static final int HAND_SIZE = 5;
  private static final int ASCENSION_MAX_HP_LOSS = 5;

  public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
    String title;
    String flavor;
    if (Settings.language == Settings.GameLanguage.ZHS) {
      title = "博丽灵梦";
      flavor = "没什么用的老熟人。";
    } else if (Settings.language == Settings.GameLanguage.JPN) {
      title = "\u666e\u901a\u306e\u9b54\u6cd5\u4f7f\u3044";
      flavor = "\u9b54\u6cd5\u306e\u68ee\u306b\u4f4f\u3093\u3067\u3044\u308b\u666e\u901a\u306e\u9b54\u6cd5\u4f7f\u3044\u3002 NL \u5149\u3068\u71b1\u306e\u9b54\u6cd5\u304c\u5f97\u610f\u3002";
    } else if (Settings.language == Settings.GameLanguage.ZHT) {
      title = "\u666e\u901a\u7684\u9b54\u6cd5\u4f7f";
      flavor = "\u4f4f\u5728\u9b54\u6cd5\u68ee\u6797\u7684\u9b54\u6cd5\u4f7f\u3002 NL \u5584\u9577\u65bc\u5149\u548c\u71b1\u7684\u9b54\u6cd5\u3002";
    }
    /*
    else if (Settings.language == Settings.GameLanguage.KOR) {
      title = "\ud3c9\ubc94\ud55c \ub9c8\ubc95\uc0ac";
      flavor = "\ub9c8\ubc95\uc758 \uc232\uc5d0 \uc0ac\ub294 \"\ud3c9\ubc94\ud55c\" \ub9c8\ubc95\uc0ac \uc785\ub2c8\ub2e4. NL \ube5b\uacfc \uc5f4 \ub9c8\ubc95\uc774 \ud2b9\uae30\uc785\ub2c8\ub2e4.";
    } else if(Settings.language == Settings.GameLanguage.FRA) {
      title = "La magicienne ordinaire";
      flavor = "La magicienne \"ordinaire\" vie dans la for\u00eat magique.  NL Sp\u00e9cialis\u00e9es dans la magie de la lumi\u00e8re et de la chaleur.";
    }
     */
    else {
      title = "The Shrine Maiden";
      flavor = "A freewheeling and haphazard shrine maiden. NL Calls upon the powers of myriads of gods.";
    }
    return new CharSelectInfo(
        title,
        flavor,
        STARTING_HP,
        MAX_HP,
        0,
        STARTING_GOLD,
        HAND_SIZE,
        this,
        getStartingRelics(),
        getStartingDeck(),
        false
    );
  }

  public AbstractCard.CardColor getCardColor() {
    return AbstractCardEnum.REIMU_COLOR;
  }

  public AbstractCard getStartCardForEvent() {
    return new NB(1);
  }

  public String getTitle(PlayerClass playerClass) {
    String title;
    if (Settings.language == GameLanguage.ZHS) {
      title = "乐园的巫女";
    } else if (Settings.language == GameLanguage.JPN) {
      title = "\u6d44\u5854\u826f\u3044\u306e\u5deb\u5973";
    } else if (Settings.language == GameLanguage.ZHT) {
      title = "\u5805\u5b9a\u7684\u5deb\u5973";
    } /*else if (Settings.language == GameLanguage.KOR) {
      title = "\ud3c9\ubc94\ud55c \ub9c8\ubc95\uc0ac";
    }
     */
     else {
      title = "The Shrine Maiden";
    }
    return title;
  }
  public Texture getCutsceneBg() {
    return ImageMaster.loadImage("img/scenes/reimuBg.png");
  }
  public List<CutscenePanel> getCutscenePanels() {
    List<CutscenePanel> panels = new ArrayList();
    panels.add(new CutscenePanel(this.ENDSCENE1,"REIMUEND"));
    panels.add(new CutscenePanel(this.ENDSCENE2));
    panels.add(new CutscenePanel(this.ENDSCENE3));
    return panels;
  }
  public Color getCardTrailColor() {
    return ReimuMod.ReimuLIGHT;
  }

  public int getAscensionMaxHPLoss() {
    return ASCENSION_MAX_HP_LOSS;
  }

  public BitmapFont getEnergyNumFont() {
    return FontHelper.energyNumFontBlue;
  }

  public void doCharSelectScreenSelectEffect() {
    CardCrawlGame.sound.playA("SELECT", MathUtils.random(-0.1F, 0.1F));
    CardCrawlGame.screenShake.shake(
        ScreenShake.ShakeIntensity.MED,
        ScreenShake.ShakeDur.SHORT,
        false
    );
  }

  public String getCustomModeCharacterButtonSoundKey() {
    return "SELECT";
  }

  public String getLocalizedCharacterName() {
    String char_name;
    if ((Settings.language == Settings.GameLanguage.JPN) || (Settings.language
        == Settings.GameLanguage.ZHS) || (Settings.language == Settings.GameLanguage.ZHT)) {
      char_name = "\u535a\u9e97\u970a\u5922";
    }
    /*
    else if (Settings.language == Settings.GameLanguage.KOR) {
      char_name = "\ub9c8\ub9ac\uc0ac";
    }
     */
     else {
      char_name = "Reimu";
    }
    return char_name;
  }

  public AbstractPlayer newInstance() {
    return new ReiMu(this.name);
  }

  @Override
  public String getVampireText() {
    return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[1];
  }

  public Color getCardRenderColor() {
    return ReimuMod.ReimuLIGHT;
  }

  public void updateOrb(int orbCount) {
    this.energyOrb.updateOrb(orbCount);
  }

  public TextureAtlas.AtlasRegion getOrb() {
    return new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ReimuMod.CARD_ENERGY_ORB), 0, 0, 24, 24);
  }

  public Color getSlashAttackColor() {
    return ReimuMod.ReimuLIGHT;
  }

  public AttackEffect[] getSpireHeartSlashEffect() {
    return new AttackEffect[]{
        AttackEffect.SLASH_HEAVY,
        AttackEffect.FIRE,
        AttackEffect.SLASH_DIAGONAL,
        AttackEffect.SLASH_HEAVY,
        AttackEffect.FIRE,
        AttackEffect.SLASH_DIAGONAL
    };
  }

  public String getSpireHeartText() {
    return CardCrawlGame.languagePack.getPowerStrings("TEXT:ReiMu").DESCRIPTIONS[0];
  }
/*
  public void damage(DamageInfo info) {
    if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (
        info.output - this.currentBlock > 0)) {
      AnimationState.TrackEntry e =
          this.state.setAnimation(0, "Hit", false);
      this.state.addAnimation(0, "Idle", true, 0.0F);
      e.setTimeScale(1.0F);
    }
    super.damage(info);
  }

 */

  public void applyPreCombatLogic() {
    super.applyPreCombatLogic();
    ReimuMod.FlyCounter = 0;
    KamiYanPower.wwe = KamiPoPower.wwe = KamiZhiPower.wwe = KamiYuePower.wwe = KamiLeiPower.wwe = KamiFengPower.wwe = KamiYangPower.wwe = false ;
    DreamingYangPower.upnub = 0 ;
  }

}
