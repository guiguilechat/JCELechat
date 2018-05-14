package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * The characters missile use efficiency, scales the damage missiles do.
 */
public class MissileDamageMultiplier
    extends DoubleAttribute
{
    public final static MissileDamageMultiplier INSTANCE = new MissileDamageMultiplier();

    @Override
    public int getId() {
        return  212;
    }

    @Override
    public int getCatId() {
        return  30;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return false;
    }
}
