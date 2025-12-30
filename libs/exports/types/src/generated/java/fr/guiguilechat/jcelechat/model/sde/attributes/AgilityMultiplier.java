package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier to the agility of an object.
 */
public class AgilityMultiplier
    extends RealAttribute
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
        return "AgilityMultiplier";
    }
}
