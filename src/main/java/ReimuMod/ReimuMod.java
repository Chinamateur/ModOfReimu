package ReimuMod;

import static ReimuMod.patches.AbstractCardEnum.REIMU_COLOR;
import static ReimuMod.patches.ReimuClassEnum.REIMU;
import ReimuMod.cards.*;
import ReimuMod.events.*;
import ReimuMod.cards.Linmeng.New.*;
import ReimuMod.cards.Linmeng.New.Authority;
import ReimuMod.potions.*;
import ReimuMod.powers.Kami.*;

import ReimuMod.cards.Linmeng.New.Dreaming;
import ReimuMod.cards.Linmeng.New.OctopathSalyBorderN;
import ReimuMod.characters.ReiMu;

import ReimuMod.powers.Kami.DreamingYangPower;
import ReimuMod.powers.Kami.KamiYangPower;
import ReimuMod.relics.MINE.*;


import basemod.*;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnPowersModifiedSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class ReimuMod implements PostExhaustSubscriber,
    PostBattleSubscriber,
    PostDungeonInitializeSubscriber,
    EditCharactersSubscriber,
    PostInitializeSubscriber,
    EditRelicsSubscriber,
    EditCardsSubscriber,
    EditStringsSubscriber,
    OnCardUseSubscriber,
    EditKeywordsSubscriber,
    OnPowersModifiedSubscriber,
    PostDrawSubscriber
{
  public static final Logger logger = LogManager.getLogger(ReimuMod.class.getName());
  private static final String MOD_BADGE = "img/ReimuUI/badge.png";
  public static int FlyCounter = 0;
  private static Properties MyReimuMod = new Properties();
  public static boolean MyReimuModbol;
    private static SpireConfig config ;
    static {
        try {
            config = new SpireConfig("vexMod", "vexModConfig",MyReimuMod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int randomset = 0;
  //card backgrounds
  private static final String ATTACK_CC = "img/512/bg_attack_Lin.png";
  private static final String SKILL_CC = "img/512/bg_skill_Lin.png";
  private static final String POWER_CC = "img/512/bg_power_Lin.png";
  private static final String ENERGY_ORB_CC = "img/512/ReimucardOrb.png";

  private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_Lin.png";
  private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_Lin.png";
  private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_Lin.png";
  private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/ReimucardOrb.png";
  public static final Color ReimuLIGHT = new Color(0.9F, 0.4F, 0.5F,0.9F).cpy();
  public static final String CARD_ENERGY_ORB = "img/ReimuUI/energyOrb.png";

  private static final String MY_CHARACTER_BUTTON = "img/charSelect/ReimuButton.png";
  private static final String REIMU_PORTRAIT = "img/charSelect/ReimuPortrait.png";

    private static final String CARD_STRING_ZH = "localization/Reimu_cards.json";
    private static final String RELIC_STRING_ZH = "localization/Reimu_relics.json";
    private static final String POWER_STRING_ZH = "localization/Reimu_powers.json";
    private static final String POTION_STRING_ZH = "localization/Reimu_potions.json";
    private static final String KEYWORD_STRING_ZH = "localization/Reimu_keywords.json";
    private static final String EVENT_PATH_ZHS = "localization/Reimu_events.json";

    private static final String CARD_STRING_ZHT = "localization/zht/Reimu_cards.json";
    private static final String RELIC_STRING_ZHT = "localization/zht/Reimu_relics.json";
    private static final String POWER_STRING_ZHT = "localization/zht/Reimu_powers.json";
    private static final String POTION_STRING_ZHT = "localization/zht/Reimu_potions.json";
    private static final String KEYWORD_STRING_ZHT = "localization/zht/Reimu_keywords.json";
    private static final String EVENT_PATH_ZHT = "localization/zht/Reimu_events.json";

    private static final String CARD_STRING_EN = "localization/en/Reimu_cards.json";
    private static final String RELIC_STRING_EN = "localization/en/Reimu_relics.json";
    private static final String POWER_STRING_EN = "localization/en/Reimu_powers.json";
    private static final String POTION_STRING_EN = "localization/en/Reimu_potions.json";
    private static final String KEYWORD_STRING_EN = "localization/en/Reimu_keywords.json";
    private static final String EVENT_PATH_EN = "localization/en/Reimu_events.json";



    //public static ArrayList<AbstractCard> cardsToAdd;

    //private Properties marisaModDefaultProp = new Properties();

  public static ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();

    //private ArrayList<AbstractRelic> relicsToAdd = new ArrayList<>();

  public ReimuMod() {
    BaseMod.subscribe(this);
    logger.info("creating the color : REIMU_COLOR");
    BaseMod.addColor(
        REIMU_COLOR,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ATTACK_CC,
        SKILL_CC,
        POWER_CC,
        ENERGY_ORB_CC,
        ATTACK_CC_PORTRAIT,
        SKILL_CC_PORTRAIT,
        POWER_CC_PORTRAIT,
        ENERGY_ORB_CC_PORTRAIT,
        CARD_ENERGY_ORB
    );

      //MyReimuMod.setProperty("MyReimuModbol", "TRUE");
      try {
          logger.info("#########################################################");
          //final SpireConfig config ;
          config.load();
          MyReimuModbol = config.getBool("MyReimuModbol");
          logger.info("讀取設置="+MyReimuModbol);
          logger.info("config done editing strings"+config.getBool("MyReimuModbol"));
          logger.info("MyReimuMod done editing strings"+MyReimuMod.getProperty("MyReimuModbol"));
      } catch (Exception e) {
          e.printStackTrace();
      }
    
    /*
    BaseMod.addColor(
        AbstractCardEnum.REIMU_DERIVATIONS,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ReimuLIGHT,
        ATTACK_CC,
        SKILL_CC,
        POWER_CC,
        ENERGY_ORB_CC,
        ATTACK_CC_PORTRAIT,
        SKILL_CC_PORTRAIT,
        POWER_CC_PORTRAIT,
        ENERGY_ORB_CC_PORTRAIT,
        CARD_ENERGY_ORB
    );
     */
  }
  

  //############################################################
  // @ 设置自定义角色
  //############################################################
  public void receiveEditCharacters() {
    logger.info("灵梦MOD:开始导入角色");
    logger.info("add " + REIMU.toString());
    BaseMod.addCharacter(
        new ReiMu("Reimu"),
        MY_CHARACTER_BUTTON,
            REIMU_PORTRAIT,
            REIMU
    );
    logger.info("灵梦MOD:角色导入完毕");
  }

  //############################################################
  // @ 设置自定义遗物
  //############################################################
  public void receiveEditRelics() {
    logger.info("灵梦MOD:开始导入遗物");

    BaseMod.addRelicToCustomPool(
            new FlyPower(), REIMU_COLOR);
    //BaseMod.addRelicToCustomPool(new danmuguizhi(), REIMU_COLOR);
    BaseMod.addRelicToCustomPool(new TurtleShell(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new FadedRune(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new Bow_fan(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new ShineYinYang(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new CheatingSign(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new DonationBox(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new KaguraBell(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new BodyOfKamiR(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new Salt(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new CrownOfHeavens(), REIMU_COLOR);
      BaseMod.addRelicToCustomPool(new SkyOrb(), REIMU_COLOR);

      RelicLibrary.add(new TentaclePot());
      RelicLibrary.add(new RedLine());
      RelicLibrary.add(new ZunR());
      RelicLibrary.add(new Tea());
      RelicLibrary.add(new YidongShenshe());
      RelicLibrary.add(new PaintedHorse());
      RelicLibrary.add(new PureWhite());
      RelicLibrary.add(new RuthlessExorcism());
    RelicLibrary.add(new CrazyTorch());
    RelicLibrary.add(new Mastiff());
    logger.info("灵梦MOD:遗物导入结束");
  }

  public void receiveEditCards() {
    logger.info("灵梦MOD:开始准备导入卡牌");

    loadCardsToAdd();

    logger.info("灵梦MOD:开始导入卡牌");

    for (AbstractCard card : cardsToAdd) {
      logger.info("灵梦MOD导入卡牌 : " + card.name);
      BaseMod.addCard(card);
    }

    logger.info("灵梦MOD:导入卡牌结束");
  }

  public static void initialize() {
    new ReimuMod();
  }

  @Override
  public void receivePostExhaust(AbstractCard c) {//抽卡
    // TODO Auto-generated method stub
  }

  @Override
  public void receivePostBattle(AbstractRoom r) {//进入战斗
    FlyCounter = 0;
    KamiYanPower.wwe = KamiPoPower.wwe = KamiZhiPower.wwe = KamiYuePower.wwe = KamiLeiPower.wwe = KamiFengPower.wwe = KamiYangPower.wwe = false ;
    DreamingYangPower.upnub = 0 ;
  }

  @Override//用卡时
  public void receiveCardUsed(AbstractCard card) {//打出卡牌
  }

  @Override
  public void receivePowersModified() {
      logger.info("########## 灵梦MOD : PowersModified ##########");
    // TODO Auto-generated method stub

  }

  @Override
  public void receivePostDungeonInitialize() {
      logger.info("########## 灵梦MOD : PostDungeonInitialize ##########");
    // TODO Auto-generated method stub
  }

  @Override
  public void receivePostDraw(AbstractCard arg0) {
      logger.info("########## 灵梦MOD : PostDraw ##########");
    // TODO Auto-generated method stub
  }

  private static String loadJson(String jsonPath) {
    return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
  }

  @Override
  public void receiveEditKeywords() {
    logger.info("灵梦MOD:导入关键词");

    String keywordsPath;
    if (Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) {
        keywordsPath = KEYWORD_STRING_ZH;
    }else {
        keywordsPath = KEYWORD_STRING_EN;
    }
    /*
    switch (Settings.language) {
     // case ZHT:
       //   keywordsPath = KEYWORD_STRING_ZHT;
      //  break;
      case ZHS:
       keywordsPath = KEYWORD_STRING_ZH;
       break;
      default:
        keywordsPath = KEYWORD_STRING_EN;
        break;
    }

     */

     // keywordsPath = KEYWORD_STRING_ZH;

    Gson gson = new Gson();
    Keywords keywords;
    keywords = gson.fromJson(loadJson(keywordsPath), Keywords.class);
    for (Keyword key : keywords.keywords) {
      logger.info("灵梦MOD读取关键词文件 : " + key.NAMES[0]);
      BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
    }
    logger.info("灵梦MOD关键词读取完毕");
  }

  @Override
  public void receiveEditStrings() {
    logger.info("start editing strings");

    String relicStrings,
        cardStrings,
        powerStrings,
        potionStrings,
        eventStrings,
        relic,
        card,
        power,
        potion,
        event;

    if (Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) {
      logger.info("lang == zh");
      card = CARD_STRING_ZH;
      relic = RELIC_STRING_ZH;
      power = POWER_STRING_ZH;
      potion = POTION_STRING_ZH;
      event = EVENT_PATH_ZHS;
   }
    else {
      logger.info("lang == eng");
      card = CARD_STRING_EN;
      relic = RELIC_STRING_EN;
      power = POWER_STRING_EN;
      potion = POTION_STRING_EN;
      event = EVENT_PATH_EN;
    }



    relicStrings = Gdx.files.internal(relic).readString(
        String.valueOf(StandardCharsets.UTF_8)
    );
    BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    cardStrings = Gdx.files.internal(card).readString(
        String.valueOf(StandardCharsets.UTF_8)
    );
    BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
    powerStrings = Gdx.files.internal(power).readString(
        String.valueOf(StandardCharsets.UTF_8)
    );
    BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
    potionStrings = Gdx.files.internal(potion).readString(
        String.valueOf(StandardCharsets.UTF_8)
    );
    BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);

    eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
    BaseMod.loadCustomStrings(EventStrings.class, eventStrings);

    logger.info("done editing strings");
  }

  @Override
  public void receivePostInitialize() {
     // CustomUnlockBundle R1 = new CustomUnlockBundle(AbstractUnlock.UnlockType.CARD,Dreaming2.ID,YinYang.ID,ZunPunch.ID);
     // BaseMod.addUnlockBundle(R1,REIMU,1);
      String labelText;
    final ModPanel MYsettingsPanel = new ModPanel();
    final Texture badge = ImageMaster.loadImage(MOD_BADGE);

      if (Settings.language == Settings.GameLanguage.ZHS) {
          labelText = "使用测试版灵梦模型?";
      } else {
          labelText = "Use old Reimu model?";
      }
      final ModLabeledToggleButton enableReimuButton =
              new ModLabeledToggleButton(
                      labelText,
                      350.0f,
                      700.0f,
                      Settings.CREAM_COLOR,
                      FontHelper.charDescFont,
                      MyReimuModbol,
                      MYsettingsPanel,
                      (label) -> {
                      },
                      (button) -> {
                          MyReimuModbol = button.enabled;
                          //MyReimuMod.setProperty(,button.enabled );
                          config.setBool("MyReimuModbol",button.enabled);
                          MyReimuMod.setProperty("MyReimuModbol",button.enabled?"TRUE":"FALSE");
                          try {
                              //MyReimuMod.save();
                              config.save();
                          } catch (IOException var3) {
                              var3.printStackTrace();
                          }
                          //try {
                              //final SpireConfig config = new SpireConfig("vexMod", "vexModConfig", MyReimuMod);
                              //AutoComplete.resetAndSuggest();
                             // config.setBool("enablePlaceholder", MyReimuModbol);
                              //config.save();
                         // } catch (Exception e) {
                         //     e.printStackTrace();
                         // }
                      });
      MYsettingsPanel.addUIElement(enableReimuButton);
/*
      ModLabel buttonLabel = new ModLabel("", 475.0F, 400.0F, MYsettingsPanel, (me) -> {
          if (me.parent.waitingOnEvent) {
              me.text = "Press key";
          } else {
              me.text = "Change console hotkey (" + Input.Keys.toString(DevConsole.toggleKey) + ")";
          }

      });
      MYsettingsPanel.addUIElement(buttonLabel);
      ModButton consoleKeyButton = new ModButton(350.0F, 350.0F, MYsettingsPanel, (me) -> {
          me.parent.waitingOnEvent = true;

          Gdx.input.setInputProcessor(new InputAdapter() {
              public boolean keyUp(int keycode) {
                  DevConsole.toggleKey = keycode;
                  try {
                      config.setString("console-key", Input.Keys.toString(keycode));
                      me.parent.waitingOnEvent = false;
                      Gdx.input.setInputProcessor(oldInputProcessor);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                  return true;
              }
          });
      });
      MYsettingsPanel.addUIElement(consoleKeyButton);

 */

      logger.info("准备导入事件");
      BaseMod.addEvent(yidonshenshe.ID, yidonshenshe.class, Exordium.ID);
      BaseMod.addEvent(ZunEvent.ID, ZunEvent.class, TheCity.ID);
      logger.info("应该导入好了？");
    BaseMod.registerModBadge(
        badge,
        "Hakurei Reimu(博丽灵梦)",
        " 巾凡 , 猫车 , Sam , 群里的大家 ",
        "A Mod of the poor miko girl from Touhou Project(",
            MYsettingsPanel
    );
      BaseMod.addPotion(
              GrandPatriotsElixir.class,
              Color.CYAN.cpy(),
              Color.GREEN.cpy(),
              null,
              "GrandPatriotsElixir:ReiMu"
      );
      BaseMod.addPotion(
              MagicPotion.class,
              Color.CYAN.cpy(),
              Color.BLUE.cpy(),
              Color.CYAN.cpy(),
              "MagicPotion:ReiMu"
      );

      BaseMod.addPotion(
              LiquidFlight.class,
              new Color(472670463),
              new Color(676576511),
              null,
              "LiquidFlight:ReiMu",
              REIMU
      );
      BaseMod.addPotion(
              BottledKami.class,
              //null,
              //null,
              //null,
            Color.WHITE.cpy(),
            Color.LIME.cpy(),
              Color.GOLD.cpy(),
              "BottledKami:ReiMu",
              REIMU
      );
      BaseMod.addPotion(
              HakureiPotion.class,
              Color.BLACK.cpy(),
              Color.WHITE.cpy(),
              null,
              "HakureiPotion:ReiMu",
              REIMU
      );
      BaseMod.addPotion(
              ZuzhouPotion.class,
              Color.GRAY.cpy(),
              Color.LIGHT_GRAY.cpy(),
              Color.DARK_GRAY.cpy(),
              "ZuzhouPotion:ReiMu"
      );
      
      BaseMod.addPotion(
              PinkPotion.class,
              Color.PINK.cpy(),
              ReimuLIGHT,
              null,
              "PinkPotion:ReiMu"
      );
  }

  private void loadCardsToAdd() {
        cardsToAdd.clear();
        cardsToAdd.add(new Strike_Lin());
        cardsToAdd.add(new Defend_Lin());
        cardsToAdd.add(new Stagnation());
        //cardsToAdd.add(new NDoubleB());
        //cardsToAdd.add(new NDamage());
        cardsToAdd.add(new SubcavityFantasy());//亚空穴
        //cardsToAdd.add(new Nudge());//微移
        cardsToAdd.add(new PrepareRite());//准备仪式
        cardsToAdd.add(new Amulet());//护身符

       // cardsToAdd.add(new NJadeStrike());//灵玉打击
        cardsToAdd.add(new PayForSelf());//自业自得
        cardsToAdd.add(new OrbThrow());//明珠暗投
        cardsToAdd.add(new LearningPray());//学业
        cardsToAdd.add(new CareerPray());//事业
        cardsToAdd.add(new SafetyPray());//安全
        //cardsToAdd.add(new Sign(-1,-1));
        cardsToAdd.add(new Determination());//底力
        cardsToAdd.add(new TakeOff());//起飞
        //cardsToAdd.add(new AirSuppress());//空中压制
        cardsToAdd.add(new AirStrike());//空中打击
        cardsToAdd.add(new SealNeedle());//封魔针
        cardsToAdd.add(new UpKicking());//升空腿
        cardsToAdd.add(new Revelation());//神启
        cardsToAdd.add(new RainPray2());//祈雨之仪

        cardsToAdd.add(new OctopathSalyBorderN());//八方龙杀阵
        cardsToAdd.add(new DependentForm());//神依形态
        cardsToAdd.add(new RunawayExorcism());//驱魔乱舞
        cardsToAdd.add(new ManaGlint());//魔净
        cardsToAdd.add(new FantasyMoon());//幻想之月
        cardsToAdd.add(new MikOfLucky());//强运巫女
        cardsToAdd.add(new CardOfSanctity());//天降宝札
        //cardsToAdd.add(new WealthPray());//财运祈愿
        cardsToAdd.add(new LovePray());//恋爱祈愿
        cardsToAdd.add(new YinYang());//旧阴阳飞鸟
        cardsToAdd.add(new TightArray());//紧缚阵
        cardsToAdd.add(new Enchanting());//封魔阵
        cardsToAdd.add(new SubAcupoint());//点穴
        cardsToAdd.add(new AlertArray());
        cardsToAdd.add(new WonderfulDomain());
        cardsToAdd.add(new Meditation());
        cardsToAdd.add(new Crusade());
        cardsToAdd.add(new ForcedLanding());
        cardsToAdd.add(new InherentArray());
        cardsToAdd.add(new YinYangSange());
        cardsToAdd.add(new SpringWay());
        //cardsToAdd.add(new Sacrifice());//祭品
        cardsToAdd.add(new PassingAway());
        cardsToAdd.add(new BarrageOfMoneybags());

        cardsToAdd.add(new CollectionFlower2());
        cardsToAdd.add(new DoubleButterfly());
        cardsToAdd.add(new ShadowOfCrow2());
        cardsToAdd.add(new EmergencyTakeOff());
        cardsToAdd.add(new AirManeuver());//空中特技

        cardsToAdd.add(new NineCharacters());
        cardsToAdd.add(new KamiRite());
        cardsToAdd.add(new PrayRite());
        cardsToAdd.add(new KamiSacrifice());
        cardsToAdd.add(new Authority());
        cardsToAdd.add(new CleanRite());
        cardsToAdd.add(new KaguraRite());
        cardsToAdd.add(new ComingRite());
        //cardsToAdd.add(new KamiComing(-1));
        cardsToAdd.add(new BlastKick2());
        //cardsToAdd.add(new BodyOfKami());//玉神体
        cardsToAdd.add(new PhosphorusFlame());
        cardsToAdd.add(new HakureiPhantom());//玻璃护肤
        //cardsToAdd.add(new SolemnJudgment());
        //cardsToAdd.add(new SolemnWishes());
        cardsToAdd.add(new SpiritualOutbreak2());//灵击
      cardsToAdd.add(new Free());//自由自在
      cardsToAdd.add(new MountainWay());//青山
      cardsToAdd.add(new SubcavityInstant());//刹那
      cardsToAdd.add(new UniversalSpiritual());//万有灵力
      cardsToAdd.add(new Shading());//间隙神隐
      //cardsToAdd.add(new GreedyExorcism());//贪婪大币
      cardsToAdd.add(new Conquer());//大降服

      //cardsToAdd.add(new BloodPurification());
      //cardsToAdd.add(new SacrificeDraw());//牺牲抽卡
      //cardsToAdd.add(new BodyOfKami(-1));
      //cardsToAdd.add(new MyTest());
      //cardsToAdd.add(new BlasphemyArray());//渎神
      cardsToAdd.add(new FantasyBorder());

      cardsToAdd.add(new Dreaming());//梦想天生
      cardsToAdd.add(new Dreaming2());//梦想封印

      cardsToAdd.add(new Mountainwind());//深山颪
      cardsToAdd.add(new Intuition());//直感
      cardsToAdd.add(new SpreadBorder2());//扩散结界
      cardsToAdd.add(new OctopathBindingBorder2());//八方鬼缚阵
      //cardsToAdd.add(new SpreadBorder());//扩散结界
      //cardsToAdd.add(new OctopathBindingBorder());//八方鬼缚阵

      //cardsToAdd.add(new FoulBorder());//犯规结界
      cardsToAdd.add(new FoulBorder2());//犯规结界2
      cardsToAdd.add(new KyokaiOfKami());//结灵
      cardsToAdd.add(new Gathering());//神灵的境界
      cardsToAdd.add(new Poor());//贫乏
      cardsToAdd.add(new ZunPunch());//醉拳
      cardsToAdd.add(new FineLineFantasy());//幻想一重

      cardsToAdd.add(new YinYangSign());
      cardsToAdd.add(new CeremonyResonance());
      cardsToAdd.add(new NB(0));

  }

  class Keywords { Keyword[] keywords;}
}
