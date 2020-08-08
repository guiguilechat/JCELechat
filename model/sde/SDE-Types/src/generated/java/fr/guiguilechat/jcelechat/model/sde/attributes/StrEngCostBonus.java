package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Cost bonus for Engineering Complexes Structures
 */
public class StrEngCostBonus
    extends DoubleAttribute
{
    public static final StrEngCostBonus INSTANCE = new StrEngCostBonus();

    @Override
    public int getId() {
        return  2601;
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
        return "StrEngCostBonus";
    }
}
