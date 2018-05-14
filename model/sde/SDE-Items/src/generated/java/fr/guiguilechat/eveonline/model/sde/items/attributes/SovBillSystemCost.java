package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The sum of this attribute on the claim markers, Infrastructure hub, and each upgrade is the systems base cost. 
 */
public class SovBillSystemCost
    extends IntAttribute
{
    public final static SovBillSystemCost INSTANCE = new SovBillSystemCost();

    @Override
    public int getId() {
        return  1603;
    }

    @Override
    public int getCatId() {
        return  7;
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
}
