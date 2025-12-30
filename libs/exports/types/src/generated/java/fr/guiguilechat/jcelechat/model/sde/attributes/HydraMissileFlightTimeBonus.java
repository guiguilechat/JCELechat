package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class HydraMissileFlightTimeBonus
    extends RealAttribute
{
    public static final HydraMissileFlightTimeBonus INSTANCE = new HydraMissileFlightTimeBonus();

    @Override
    public int getId() {
        return  3030;
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
        return "HydraMissileFlightTimeBonus";
    }
}
