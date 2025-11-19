package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusGasCloudDurationRoleBonusOreMiningDestroyer
    extends IntAttribute
{
    public static final ShipBonusGasCloudDurationRoleBonusOreMiningDestroyer INSTANCE = new ShipBonusGasCloudDurationRoleBonusOreMiningDestroyer();

    @Override
    public int getId() {
        return  5952;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShipBonusGasCloudDurationRoleBonusOreMiningDestroyer";
    }
}
