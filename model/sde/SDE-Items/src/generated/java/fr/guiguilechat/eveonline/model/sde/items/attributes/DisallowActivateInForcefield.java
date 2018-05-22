package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * If set to True on a module, the module will not be allowed to activate whilst the ship is inside a starbase forcefield
 */
public class DisallowActivateInForcefield
    extends IntAttribute
{
    public final static DisallowActivateInForcefield INSTANCE = new DisallowActivateInForcefield();

    @Override
    public int getId() {
        return  1920;
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
        return "DisallowActivateInForcefield";
    }
}
