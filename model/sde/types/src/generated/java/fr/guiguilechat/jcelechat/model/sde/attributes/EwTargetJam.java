package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used for target jam effects to reduce max locked targets of victem to a negative value to ensure the victem looses its targets, use extreme value
 */
public class EwTargetJam
    extends IntAttribute
{
    public static final EwTargetJam INSTANCE = new EwTargetJam();

    @Override
    public int getId() {
        return  831;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EwTargetJam";
    }
}
