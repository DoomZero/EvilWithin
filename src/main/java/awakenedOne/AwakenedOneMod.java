package awakenedOne;


import awakenedOne.stances.AbstractAwakenedStance;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.stances.NeutralStance;
import downfall.util.CardIgnore;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;
import awakenedOne.cards.cardvars.SecondDamage;
import awakenedOne.cards.cardvars.SillyVariable;
import awakenedOne.relics.TodoItem;
import awakenedOne.util.CardArtRoller;
import awakenedOne.util.CardFilter;
import awakenedOne.util.Wiz;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class AwakenedOneMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditCharactersSubscriber,
//        OnStartBattleSubscriber,
        PostInitializeSubscriber,
        PostBattleSubscriber,
        OnStartBattleSubscriber
{

    public static Prefs colorCardsPrefs = new Prefs();

    public static final String modID = "awakened"; //TODO: Change this.

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

    @SpireEnum
    public static AbstractCard.CardTags AWAKENED;

    public static boolean isAwakened;

    public AwakenedOneMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(AwakenedOneChar.Enums.AWAKENED_BLUE, characterColor, characterColor, characterColor,
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

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
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
        AwakenedOneMod thismod = new AwakenedOneMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new AwakenedOneChar("The Awakened One", AwakenedOneChar.Enums.THE_AWAKENED_ONE),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, AwakenedOneChar.Enums.THE_AWAKENED_ONE);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new TodoItem(), AwakenedOneChar.Enums.AWAKENED_BLUE);
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
        URL url = AwakenedOneMod.class.getProtectionDomain().getCodeSource().getLocation();
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

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom){
        isAwakened = false;
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player instanceof AwakenedOneChar) {
            if (AbstractDungeon.player.stance instanceof AbstractAwakenedStance) {
                AbstractDungeon.player.stance = new NeutralStance();
                AwakenedOneChar c = (AwakenedOneChar) AbstractDungeon.player;
//                c.switchStanceVisual(NeutralStance.STANCE_ID);
            }
        }
        isAwakened = false;
    }


    @Override
    public void receivePostInitialize() {
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
}
