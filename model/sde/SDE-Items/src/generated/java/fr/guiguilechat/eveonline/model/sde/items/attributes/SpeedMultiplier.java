package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Typically scales the firing speed of a weapon.  Reducing speed means faster, strangely..
 */
public class SpeedMultiplier
    extends DoubleAttribute
{
    public final static SpeedMultiplier INSTANCE = new SpeedMultiplier();

    @Override
    public int getId() {
        return  204;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
