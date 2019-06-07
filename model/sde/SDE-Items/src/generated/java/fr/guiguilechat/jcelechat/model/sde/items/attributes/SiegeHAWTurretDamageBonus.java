package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to HAW turret damage
 */
public class SiegeHAWTurretDamageBonus
    extends IntAttribute
{
    public static final SiegeHAWTurretDamageBonus INSTANCE = new SiegeHAWTurretDamageBonus();

    @Override
    public int getId() {
        return  2820;
    }

    @Override
    public int getCatId() {
        return  37;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "SiegeHAWTurretDamageBonus";
    }
}
