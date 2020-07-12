package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Used by Battlecruisers for large turret CPU reduction
 */
public class BcLargeTurretCPU
    extends DoubleAttribute
{
    public static final BcLargeTurretCPU INSTANCE = new BcLargeTurretCPU();

    @Override
    public int getId() {
        return  1787;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "BcLargeTurretCPU";
    }
}