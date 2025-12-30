package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class ShipRoleBonusGasHarvesterDuration
    extends RealAttribute
{
    public static final ShipRoleBonusGasHarvesterDuration INSTANCE = new ShipRoleBonusGasHarvesterDuration();

    @Override
    public int getId() {
        return  3225;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShipRoleBonusGasHarvesterDuration";
    }
}
