package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * distance from maximum range at which accuracy has fallen by half
 */
public class Falloff
    extends IntAttribute
{
    public static final Falloff INSTANCE = new Falloff();

    @Override
    public int getId() {
        return  158;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "Falloff";
    }
}
