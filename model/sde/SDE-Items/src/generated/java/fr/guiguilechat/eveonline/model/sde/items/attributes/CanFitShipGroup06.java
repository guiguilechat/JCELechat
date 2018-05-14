package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Can be fitted to
 */
public class CanFitShipGroup06
    extends IntAttribute
{
    public final static CanFitShipGroup06 INSTANCE = new CanFitShipGroup06();

    @Override
    public int getId() {
        return  1879;
    }

    @Override
    public int getCatId() {
        return  1;
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
}
