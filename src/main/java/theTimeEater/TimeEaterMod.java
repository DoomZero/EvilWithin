package theTimeEater;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.widepotions.WidePotionsMod;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import downfall.util.CardIgnore;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;
import theTimeEater.cards.cardvars.SecondDamage;
import theTimeEater.cards.cardvars.SillyVariable;
import theTimeEater.potions.TempoSwitchPotion;
import theTimeEater.potions.TimeLockEnemyPotion;
import theTimeEater.potions.TimeLockSelfPotion;
import theTimeEater.powers.ClockPower;
import theTimeEater.powers.TimeLockPower;
import theTimeEater.relics.TodoItem;
import theTimeEater.util.CardArtRoller;
import theTimeEater.util.CardFilter;
import theTimeEater.util.Wiz;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;

import static theTimeEater.util.Wiz.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class TimeEaterMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditCharactersSubscriber,
//        EditKeywordsSubscriber,
        PostInitializeSubscriber,
        PostBattleSubscriber,
        OnStartBattleSubscriber {

    public static Prefs colorCardsPrefs = new Prefs();

    public static final String modID = "timeeatermod";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";


    public TimeEaterMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheTimeEater.Enums.TIME_EATER_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        TimeEaterMod thismod = new TimeEaterMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheTimeEater(TheTimeEater.characterStrings.NAMES[1], TheTimeEater.Enums.THE_TIME_EATER),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheTimeEater.Enums.THE_TIME_EATER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new TodoItem(), TheTimeEater.Enums.TIME_EATER_COLOR);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SillyVariable());
        BaseMod.addDynamicVariable(new SecondDamage());
        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void autoAddCards()
            throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        URL url = TimeEaterMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().getClassLoader().loadClass(cls.getName()).newInstance();
            BaseMod.addCard(card);

        }
    }

    public void addPotions() {

        BaseMod.addPotion(TempoSwitchPotion.class, Color.CHARTREUSE, Color.CORAL, Color.MAROON, TempoSwitchPotion.POTION_ID);
        BaseMod.addPotion(TimeLockSelfPotion.class, Color.CYAN, Color.CORAL, Color.MAROON, TimeLockSelfPotion.POTION_ID);
        BaseMod.addPotion(TimeLockEnemyPotion.class, Color.RED, Color.CORAL, Color.MAROON, TimeLockEnemyPotion.POTION_ID);

        if (Loader.isModLoaded("widepotions")) {
            WidePotionsMod.whitelistSimplePotion(TempoSwitchPotion.POTION_ID);
            WidePotionsMod.whitelistSimplePotion(TimeLockSelfPotion.POTION_ID);
            WidePotionsMod.whitelistSimplePotion(TimeLockEnemyPotion.POTION_ID);
        }
    }

    @Override
    public void receivePostInitialize() {
        addPotions();

        String prefsLoc = (Settings.isBeta ? "betaPreferences" : "Preferences") + File.separator + modID + "colorCardsInfo";
        if (!Gdx.files.local(prefsLoc).exists()) {
            try {
                System.out.println("Copying default preferences");
                byte[] data = Gdx.files.classpath(modID + "Resources/CardData.json").readBytes();
                Path path = FileSystems.getDefault().getPath(Gdx.files.getLocalStoragePath() + prefsLoc, new String[0]);
                Files.write(path, data, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.SYNC);
            } catch (Exception ex) {
                System.out.println("Error: Failed to copy default preferences!");
                System.out.println(ex.getMessage());
                System.out.println(ex.toString());
            }
        }
        colorCardsPrefs = SaveHelper.getPrefs(modID + "colorCardsInfo");
    }

    public static CardArtRoller.ReskinInfo getNewReskinInfo(AbstractCard.CardType t) {
        Random rng = new Random();
        ArrayList<AbstractCard> cardsList = Wiz.getCardsMatchingPredicate(s -> s.type == t && BaseMod.isBaseGameCardColor(s.color), true);
        String q = Wiz.getRandomItem(cardsList, rng).cardID;
        return new CardArtRoller.ReskinInfo(q, rng.random(0.35f, 0.65f), rng.random(0.35f, 0.65f), rng.random(0.35f, 0.65f), rng.random(0.35f, 0.65f), rng.randomBoolean());
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        tempo = tempos.FORWARD;
        applyToSelf(new ClockPower());
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        TimeLockPower q = (TimeLockPower) AbstractDungeon.player.getPower(TimeLockPower.POWER_ID);
        if (q != null) {
            q.explode();
        }
    }

    public enum tempos {
        FORWARD,
        REWIND,
        PAUSE
    }

    public static tempos tempo = tempos.FORWARD;
}
