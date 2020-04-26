package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Research speed 
 */
public class ManufacturingTimeResearchSpeed
    extends IntAttribute
{
    public static final ManufacturingTimeResearchSpeed INSTANCE = new ManufacturingTimeResearchSpeed();

    @Override
    public int getId() {
        return  385;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "ManufacturingTimeResearchSpeed";
    }
}
