package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
 */
public class RemoteAssistanceImpedance
    extends DoubleAttribute
{
    public final static RemoteAssistanceImpedance INSTANCE = new RemoteAssistanceImpedance();

    @Override
    public int getId() {
        return  2135;
    }

    @Override
    public int getCatId() {
        return  36;
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
        return "RemoteAssistanceImpedance";
    }
}
