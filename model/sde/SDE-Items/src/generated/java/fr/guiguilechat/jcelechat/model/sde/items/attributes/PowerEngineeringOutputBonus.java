package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Autogenerated skill attribute, PowerOutputBonus
 */
public class PowerEngineeringOutputBonus
    extends DoubleAttribute
{
    public final static PowerEngineeringOutputBonus INSTANCE = new PowerEngineeringOutputBonus();

    @Override
    public int getId() {
        return  313;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "PowerEngineeringOutputBonus";
    }
}