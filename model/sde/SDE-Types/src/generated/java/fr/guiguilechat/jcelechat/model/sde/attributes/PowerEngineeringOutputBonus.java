package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Autogenerated skill attribute, PowerOutputBonus
 */
public class PowerEngineeringOutputBonus
    extends RealAttribute
{
    public static final PowerEngineeringOutputBonus INSTANCE = new PowerEngineeringOutputBonus();

    @Override
    public int getId() {
        return  313;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "PowerEngineeringOutputBonus";
    }
}
