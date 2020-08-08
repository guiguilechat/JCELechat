package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Indicates the percentage amount redistributed each cycle for resistance shift modules
 */
public class ResistanceShiftAmount
    extends IntAttribute
{
    public static final ResistanceShiftAmount INSTANCE = new ResistanceShiftAmount();

    @Override
    public int getId() {
        return  1849;
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
        return "ResistanceShiftAmount";
    }
}
