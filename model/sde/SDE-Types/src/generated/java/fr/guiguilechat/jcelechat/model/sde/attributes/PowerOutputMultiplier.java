package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multipier to power core output.
 */
public class PowerOutputMultiplier
    extends DoubleAttribute
{
    public static final PowerOutputMultiplier INSTANCE = new PowerOutputMultiplier();

    @Override
    public int getId() {
        return  145;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "PowerOutputMultiplier";
    }
}
