package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
 */
public class RemoteAssistanceImpedance
    extends RealAttribute
{
    public static final RemoteAssistanceImpedance INSTANCE = new RemoteAssistanceImpedance();

    @Override
    public int getId() {
        return  2135;
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
        return "RemoteAssistanceImpedance";
    }
}
