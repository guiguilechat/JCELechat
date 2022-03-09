package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * An effect can check this to indicate that module activation requires ship to have an active Industrial Core module.
 */
public class ActivationRequiresActiveIndustrialCore
    extends IntAttribute
{
    public static final ActivationRequiresActiveIndustrialCore INSTANCE = new ActivationRequiresActiveIndustrialCore();

    @Override
    public int getId() {
        return  3265;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "ActivationRequiresActiveIndustrialCore";
    }
}
