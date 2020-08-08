package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Amount of time that has to be waited after the deactivation of this module until it can be reactivated.
 */
public class ModuleReactivationDelay
    extends IntAttribute
{
    public static final ModuleReactivationDelay INSTANCE = new ModuleReactivationDelay();

    @Override
    public int getId() {
        return  669;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "ModuleReactivationDelay";
    }
}
