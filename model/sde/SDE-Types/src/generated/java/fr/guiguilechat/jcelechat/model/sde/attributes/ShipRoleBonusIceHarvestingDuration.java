package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class ShipRoleBonusIceHarvestingDuration
    extends RealAttribute
{
    public static final ShipRoleBonusIceHarvestingDuration INSTANCE = new ShipRoleBonusIceHarvestingDuration();

    @Override
    public int getId() {
        return  3178;
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
        return "ShipRoleBonusIceHarvestingDuration";
    }
}
