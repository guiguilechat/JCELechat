package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Used by Battlecruisers for large turret capacitor reduction
 */
public class BcLargeTurretCap
    extends RealAttribute
{
    public static final BcLargeTurretCap INSTANCE = new BcLargeTurretCap();

    @Override
    public int getId() {
        return  1788;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "BcLargeTurretCap";
    }
}
