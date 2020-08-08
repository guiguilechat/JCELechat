package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class RefiningYieldMoonOres
    extends DoubleAttribute
{
    public static final RefiningYieldMoonOres INSTANCE = new RefiningYieldMoonOres();

    @Override
    public int getId() {
        return  2445;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.5;
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
        return "RefiningYieldMoonOres";
    }
}
