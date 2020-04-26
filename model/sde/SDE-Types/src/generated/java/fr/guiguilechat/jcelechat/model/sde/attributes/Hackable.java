package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Defines whether an entity can be hacked or not.
 */
public class Hackable
    extends IntAttribute
{
    public static final Hackable INSTANCE = new Hackable();

    @Override
    public int getId() {
        return  1927;
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
        return "Hackable";
    }
}
