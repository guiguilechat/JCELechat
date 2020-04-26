package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * +/- bonus to the memory of a character.
 */
public class MemoryBonus
    extends IntAttribute
{
    public static final MemoryBonus INSTANCE = new MemoryBonus();

    @Override
    public int getId() {
        return  177;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MemoryBonus";
    }
}
