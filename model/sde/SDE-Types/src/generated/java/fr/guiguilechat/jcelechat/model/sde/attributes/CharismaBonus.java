package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * +/- bonus to the charisma of a character.
 */
public class CharismaBonus
    extends IntAttribute
{
    public static final CharismaBonus INSTANCE = new CharismaBonus();

    @Override
    public int getId() {
        return  175;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "CharismaBonus";
    }
}
