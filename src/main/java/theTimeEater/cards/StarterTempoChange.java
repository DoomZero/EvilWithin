package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.cards.OctoChoiceCard;
import downfall.util.OctopusCard;
import theHexaghost.HexaMod;
import theTimeEater.TheTimeEater;
import theTimeEater.actions.EnterTempoAction;
import theTimeEater.actions.SwitchTempoAction;

import java.util.ArrayList;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class StarterTempoChange extends AbstractTimeEaterCard implements OctopusCard {
    public final static String ID = makeID(StarterTempoChange.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    String[] EXTENDED_DESCRIPTION;

    public StarterTempoChange() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public ArrayList<OctoChoiceCard> choiceList() {

        ArrayList<OctoChoiceCard> cardList = new ArrayList<>();
        cardList.add(new OctoChoiceCard("octo:OctoRewind",  this.name, HexaMod.makeCardPath("Float.png"), this.EXTENDED_DESCRIPTION[1]));
        cardList.add(new OctoChoiceCard("octo:OctoNothing", this.name, HexaMod.makeCardPath("Float.png"), this.EXTENDED_DESCRIPTION[2]));
        cardList.add(new OctoChoiceCard("octo:OctoForward", this.name, HexaMod.makeCardPath("Float.png"), this.EXTENDED_DESCRIPTION[0]));

        return cardList;
    }

    public void doChoiceStuff(AbstractMonster m, OctoChoiceCard card) {
        switch (card.cardID) {
            case "octo:OctoForward":
                atb(new EnterTempoAction(TheTimeEater.tempos.FORWARD));
                break;
            case "octo:OctoRewind":
                atb(new EnterTempoAction(TheTimeEater.tempos.REWIND));
                break;
            case "octo:OctoNothing":
                break;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SwitchTempoAction());
//        atb(new OctoChoiceAction(m, this));
        scry();
    }

    public void upp() {
        upgradeBlock(3);
    }
}