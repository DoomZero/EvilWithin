package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theTimeEater.util.TexLoader;

import static theTimeEater.util.Wiz.*;

public abstract class AbstractTimeEaterPower extends TwoAmountPower implements CloneablePowerInterface {
    public AbstractTimeEaterPower(String NAME, String ID, PowerType powerType) {
        this(NAME, ID, powerType, AbstractDungeon.player);
    }

    public AbstractTimeEaterPower(String NAME, String ID, PowerType powerType, int amount) {
        this(NAME, ID, powerType, AbstractDungeon.player, amount);
    }

    public AbstractTimeEaterPower(String NAME, String ID, PowerType powerType, AbstractCreature owner) {
        this(NAME, ID, powerType, owner, 0);
    }

    public AbstractTimeEaterPower(String NAME, String ID, PowerType powerType, AbstractCreature owner, int amount) {
        this(NAME, ID, powerType, owner, amount, 0);
    }

    public AbstractTimeEaterPower(String NAME, String ID, PowerType powerType, AbstractCreature owner, int amount, boolean isTurnBased) {
        this(NAME, ID, powerType, owner, amount, 0, isTurnBased);
    }

    public AbstractTimeEaterPower(String NAME, String ID, PowerType powerType, AbstractCreature owner, int amount, int amount2) {
        this(NAME, ID, powerType, owner, amount, amount2, false);
    }

    public AbstractTimeEaterPower(String NAME, String ID, PowerType powerType, AbstractCreature owner, int amount, int amount2, boolean isTurnBased) {
        this.name = NAME;
        this.ID = ID;
        this.type = powerType;
        this.isTurnBased = isTurnBased;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = amount2;

        Texture normalTexture = TexLoader.getTexture("bronzeResources/images/powers/" + NAME.replaceAll("([ ])", "") + "32.png");
        Texture hiDefImage = TexLoader.getTexture("bronzeResources/images/powers/" + NAME.replaceAll("([ ])", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        this.updateDescription();
    }

    public void remove() {
        atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    public void decrement() {
        decrement(1);
    }

    public void decrement(int decreaseBy) {
        atb(new ReducePowerAction(this.owner, this.owner, this, decreaseBy));
    }

}