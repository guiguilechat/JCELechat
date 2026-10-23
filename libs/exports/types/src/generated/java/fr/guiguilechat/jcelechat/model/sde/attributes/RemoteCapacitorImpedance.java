package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Impedance against Remote Capacitor Transmitters
 */
public class RemoteCapacitorImpedance
    extends RealAttribute
{
    public static final RemoteCapacitorImpedance INSTANCE = new RemoteCapacitorImpedance();

    @Override
    public int getId() {
        return  6463;
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
        return "RemoteCapacitorImpedance";
    }
}
