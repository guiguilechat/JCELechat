package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If set, this module cannot be activated and made to autorepeat.
 */
public class DisallowRepeatingActivation
    extends IntAttribute
{
    public static final DisallowRepeatingActivation INSTANCE = new DisallowRepeatingActivation();

    @Override
    public int getId() {
        return  1014;
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
        return "DisallowRepeatingActivation";
    }
}
