package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Indicates that a module or subsystem has been phased out. Primarily used by saved fittings.
 */
public class ModuleIsObsolete
    extends IntAttribute
{
    public final static ModuleIsObsolete INSTANCE = new ModuleIsObsolete();

    @Override
    public int getId() {
        return  2696;
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
        return "ModuleIsObsolete";
    }
}
