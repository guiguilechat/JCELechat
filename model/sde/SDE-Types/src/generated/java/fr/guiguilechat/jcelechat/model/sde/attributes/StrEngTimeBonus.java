package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Time bonus for Engineering Complexes Structures
 */
public class StrEngTimeBonus
    extends RealAttribute
{
    public static final StrEngTimeBonus INSTANCE = new StrEngTimeBonus();

    @Override
    public int getId() {
        return  2602;
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
        return "StrEngTimeBonus";
    }
}
