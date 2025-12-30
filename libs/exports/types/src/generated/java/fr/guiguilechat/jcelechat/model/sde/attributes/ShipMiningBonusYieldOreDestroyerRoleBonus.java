package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Role bonus for the Pioneer and Pioneer Executive Issue for mining yield amount. 
 */
public class ShipMiningBonusYieldOreDestroyerRoleBonus
    extends IntAttribute
{
    public static final ShipMiningBonusYieldOreDestroyerRoleBonus INSTANCE = new ShipMiningBonusYieldOreDestroyerRoleBonus();

    @Override
    public int getId() {
        return  5986;
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
        return "ShipMiningBonusYieldOreDestroyerRoleBonus";
    }
}
