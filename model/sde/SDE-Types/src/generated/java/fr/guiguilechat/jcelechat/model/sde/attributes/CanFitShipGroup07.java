package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class CanFitShipGroup07
    extends IntAttribute
{
    public static final CanFitShipGroup07 INSTANCE = new CanFitShipGroup07();

    @Override
    public int getId() {
        return  1880;
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
        return "CanFitShipGroup07";
    }
}
