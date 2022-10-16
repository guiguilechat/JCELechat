package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If set to 1 on a module, that module can be activated whilst under gate cloak. (Does NOT include any other cloak mode)
 */
public class CanActivateInGateCloak
    extends IntAttribute
{
    public static final CanActivateInGateCloak INSTANCE = new CanActivateInGateCloak();

    @Override
    public int getId() {
        return  3123;
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
        return false;
    }

    @Override
    public String toString() {
        return "CanActivateInGateCloak";
    }
}
