package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class RemoteCapacitorImpedanceBonus
    extends RealAttribute
{
    public static final RemoteCapacitorImpedanceBonus INSTANCE = new RemoteCapacitorImpedanceBonus();

    @Override
    public int getId() {
        return  6464;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "RemoteCapacitorImpedanceBonus";
    }
}
