package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * If this module is in use and this attribute is 1, then assistance modules cannot be used on the ship.
 */
public class DisallowAssistance
    extends IntAttribute
{
    public final static DisallowAssistance INSTANCE = new DisallowAssistance();

    @Override
    public int getId() {
        return  854;
    }

    @Override
    public int getCatId() {
        return  6;
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

    @Override
    public String toString() {
        return "DisallowAssistance";
    }
}
