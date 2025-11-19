package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Role Bonus for ORE Executive Issue Ships for critical hit chance
 */
public class ShipRoleBonusOREExecutiveIssueMiningCriticalHitChance
    extends IntAttribute
{
    public static final ShipRoleBonusOREExecutiveIssueMiningCriticalHitChance INSTANCE = new ShipRoleBonusOREExecutiveIssueMiningCriticalHitChance();

    @Override
    public int getId() {
        return  6048;
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
        return "ShipRoleBonusOREExecutiveIssueMiningCriticalHitChance";
    }
}
