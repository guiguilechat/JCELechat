package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier to the agility of an object.
 */
public class AgilityMultiplier
    extends DoubleAttribute
{
    public static final AgilityMultiplier INSTANCE = new AgilityMultiplier();

    @Override
    public int getId() {
        return  169;
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
        return "AgilityMultiplier";
    }
}
