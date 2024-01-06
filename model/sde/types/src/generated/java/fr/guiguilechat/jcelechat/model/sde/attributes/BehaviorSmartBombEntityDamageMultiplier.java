package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Controls how much of the NpcBehaviorSmartBomb effect's damage gets applied to entities
 */
public class BehaviorSmartBombEntityDamageMultiplier
    extends RealAttribute
{
    public static final BehaviorSmartBombEntityDamageMultiplier INSTANCE = new BehaviorSmartBombEntityDamageMultiplier();

    @Override
    public int getId() {
        return  3039;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "BehaviorSmartBombEntityDamageMultiplier";
    }
}
