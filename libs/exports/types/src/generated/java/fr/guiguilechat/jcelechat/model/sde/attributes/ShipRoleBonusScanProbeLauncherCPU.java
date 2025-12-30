package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipRoleBonusScanProbeLauncherCPU
    extends IntAttribute
{
    public static final ShipRoleBonusScanProbeLauncherCPU INSTANCE = new ShipRoleBonusScanProbeLauncherCPU();

    @Override
    public int getId() {
        return  5048;
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
        return false;
    }

    @Override
    public String toString() {
        return "ShipRoleBonusScanProbeLauncherCPU";
    }
}
