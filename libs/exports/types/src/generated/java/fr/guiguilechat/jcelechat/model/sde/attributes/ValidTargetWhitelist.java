package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The ID of a typeList that defines the whitelist of types that can be valid targets for this module
 */
public class ValidTargetWhitelist
    extends IntAttribute
{
    public static final ValidTargetWhitelist INSTANCE = new ValidTargetWhitelist();

    @Override
    public int getId() {
        return  5745;
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
        return "ValidTargetWhitelist";
    }
}
