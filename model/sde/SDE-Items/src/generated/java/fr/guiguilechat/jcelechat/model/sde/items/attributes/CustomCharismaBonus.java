package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to the charisma of a character specified by the player in character creation.
 */
public class CustomCharismaBonus
    extends IntAttribute
{
    public final static CustomCharismaBonus INSTANCE = new CustomCharismaBonus();

    @Override
    public int getId() {
        return  170;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "CustomCharismaBonus";
    }
}
