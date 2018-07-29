package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class ModeMWDVelocityPostDiv
    extends DoubleAttribute
{
    public final static ModeMWDVelocityPostDiv INSTANCE = new ModeMWDVelocityPostDiv();

    @Override
    public int getId() {
        return  2031;
    }

    @Override
    public int getCatId() {
        return  17;
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
        return "ModeMWDVelocityPostDiv";
    }
}
