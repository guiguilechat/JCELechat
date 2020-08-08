package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Power load multiplier for PI link levels
 */
public class PowerLoadLevelModifier
    extends DoubleAttribute
{
    public static final PowerLoadLevelModifier INSTANCE = new PowerLoadLevelModifier();

    @Override
    public int getId() {
        return  1636;
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
        return "PowerLoadLevelModifier";
    }
}
