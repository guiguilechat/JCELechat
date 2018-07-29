package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class ModeVelocityPostDiv
    extends DoubleAttribute
{
    public final static ModeVelocityPostDiv INSTANCE = new ModeVelocityPostDiv();

    @Override
    public int getId() {
        return  2003;
    }

    @Override
    public int getCatId() {
        return  17;
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
        return "ModeVelocityPostDiv";
    }
}
