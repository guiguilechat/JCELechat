package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class KineticDamageResistanceBonus
    extends RealAttribute
{
    public static final KineticDamageResistanceBonus INSTANCE = new KineticDamageResistanceBonus();

    @Override
    public int getId() {
        return  986;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "KineticDamageResistanceBonus";
    }
}
