package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipRoleBonusIceHarvesterActivationCost
    extends IntAttribute
{
    public static final ShipRoleBonusIceHarvesterActivationCost INSTANCE = new ShipRoleBonusIceHarvesterActivationCost();

    @Override
    public int getId() {
        return  3229;
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
        return "ShipRoleBonusIceHarvesterActivationCost";
    }
}
