package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class RefiningYieldNormalOres
    extends RealAttribute
{
    public static final RefiningYieldNormalOres INSTANCE = new RefiningYieldNormalOres();

    @Override
    public int getId() {
        return  2444;
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
        return "RefiningYieldNormalOres";
    }
}
