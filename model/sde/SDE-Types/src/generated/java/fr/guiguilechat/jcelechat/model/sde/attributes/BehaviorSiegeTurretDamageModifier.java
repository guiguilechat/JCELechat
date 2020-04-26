package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorSiegeTurretDamageModifier
    extends IntAttribute
{
    public static final BehaviorSiegeTurretDamageModifier INSTANCE = new BehaviorSiegeTurretDamageModifier();

    @Override
    public int getId() {
        return  2649;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "BehaviorSiegeTurretDamageModifier";
    }
}
