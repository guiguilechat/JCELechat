package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Scales the time it takes to manufacture something.
 */
public class ManufactureTimeMultiplier
    extends IntAttribute
{
    public static final ManufactureTimeMultiplier INSTANCE = new ManufactureTimeMultiplier();

    @Override
    public int getId() {
        return  219;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "ManufactureTimeMultiplier";
    }
}
