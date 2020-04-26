package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The sum of this attribute on the claim markers, Infrastructure hub, and each upgrade is the systems base cost. 
 */
public class SovBillSystemCost
    extends IntAttribute
{
    public static final SovBillSystemCost INSTANCE = new SovBillSystemCost();

    @Override
    public int getId() {
        return  1603;
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
        return "SovBillSystemCost";
    }
}
