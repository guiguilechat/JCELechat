package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Role Bonus for Perseverance Ice Mining Critical Hit Role bonus
 */
public class ShipRoleBonusPerseveranceIceMiningCriticalHitChance
    extends IntAttribute
{
    public static final ShipRoleBonusPerseveranceIceMiningCriticalHitChance INSTANCE = new ShipRoleBonusPerseveranceIceMiningCriticalHitChance();

    @Override
    public int getId() {
        return  6062;
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
        return "ShipRoleBonusPerseveranceIceMiningCriticalHitChance";
    }
}
