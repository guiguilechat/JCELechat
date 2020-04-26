package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If this module is in use and this attribute is 1, then assistance modules cannot be used on the ship.
 */
public class DisallowAssistance
    extends IntAttribute
{
    public static final DisallowAssistance INSTANCE = new DisallowAssistance();

    @Override
    public int getId() {
        return  854;
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
        return "DisallowAssistance";
    }
}
