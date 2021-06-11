package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration of the cloak-stabilization dbuff provided by a cloak module
 */
public class StabilizeCloakDuration
    extends IntAttribute
{
    public static final StabilizeCloakDuration INSTANCE = new StabilizeCloakDuration();

    @Override
    public int getId() {
        return  3118;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return "StabilizeCloakDuration";
    }
}
