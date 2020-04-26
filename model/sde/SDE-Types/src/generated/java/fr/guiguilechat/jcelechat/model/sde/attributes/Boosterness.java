package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Whether an item is a booster or not.
 */
public class Boosterness
    extends IntAttribute
{
    public static final Boosterness INSTANCE = new Boosterness();

    @Override
    public int getId() {
        return  1087;
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
        return "Boosterness";
    }
}
