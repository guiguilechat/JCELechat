package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * If set, this module cannot be activated and made to autorepeat.
 */
public class DisallowRepeatingActivation
    extends IntAttribute
{
    public final static DisallowRepeatingActivation INSTANCE = new DisallowRepeatingActivation();

    @Override
    public int getId() {
        return  1014;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "DisallowRepeatingActivation";
    }
}
