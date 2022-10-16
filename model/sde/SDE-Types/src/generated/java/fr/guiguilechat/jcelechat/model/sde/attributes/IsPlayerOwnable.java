package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Whether a station type is player ownable.
 */
public class IsPlayerOwnable
    extends IntAttribute
{
    public static final IsPlayerOwnable INSTANCE = new IsPlayerOwnable();

    @Override
    public int getId() {
        return  589;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "IsPlayerOwnable";
    }
}
