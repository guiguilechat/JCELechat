package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class ImplantSetLGRepublicFleet
    extends DoubleAttribute
{
    public static final ImplantSetLGRepublicFleet INSTANCE = new ImplantSetLGRepublicFleet();

    @Override
    public int getId() {
        return  1572;
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
        return "ImplantSetLGRepublicFleet";
    }
}
