package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class DurationECMJammerBurstProjector
    extends IntAttribute
{
    public static final DurationECMJammerBurstProjector INSTANCE = new DurationECMJammerBurstProjector();

    @Override
    public int getId() {
        return  2398;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "DurationECMJammerBurstProjector";
    }
}
