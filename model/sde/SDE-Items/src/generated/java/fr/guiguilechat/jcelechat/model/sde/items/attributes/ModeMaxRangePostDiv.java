package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class ModeMaxRangePostDiv
    extends DoubleAttribute
{
    public final static ModeMaxRangePostDiv INSTANCE = new ModeMaxRangePostDiv();

    @Override
    public int getId() {
        return  1990;
    }

    @Override
    public int getCatId() {
        return  29;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
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
        return "ModeMaxRangePostDiv";
    }
}
