package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Material bonus for Engineering Complexes Structures
 */
public class StrEngMatBonus
    extends RealAttribute
{
    public static final StrEngMatBonus INSTANCE = new StrEngMatBonus();

    @Override
    public int getId() {
        return  2600;
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
        return "StrEngMatBonus";
    }
}
