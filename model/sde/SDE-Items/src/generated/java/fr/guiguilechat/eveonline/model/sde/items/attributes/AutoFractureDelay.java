package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Delay for exploding moon mining chunk into asteroid field
 */
public class AutoFractureDelay
    extends IntAttribute
{
    public final static AutoFractureDelay INSTANCE = new AutoFractureDelay();

    @Override
    public int getId() {
        return  2698;
    }

    @Override
    public int getCatId() {
        return  4;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  10800.0;
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
        return "AutoFractureDelay";
    }
}
