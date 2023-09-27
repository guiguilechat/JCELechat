package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If set on a charge or module type, will prevent it from being activated in hazard system
 */
public class DisallowInHazardSystem
    extends IntAttribute
{
    public static final DisallowInHazardSystem INSTANCE = new DisallowInHazardSystem();

    @Override
    public int getId() {
        return  5561;
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
        return "DisallowInHazardSystem";
    }
}
