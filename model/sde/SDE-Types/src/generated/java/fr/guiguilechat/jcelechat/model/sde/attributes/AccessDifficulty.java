package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The difficulty in opening this object.
 */
public class AccessDifficulty
    extends DoubleAttribute
{
    public static final AccessDifficulty INSTANCE = new AccessDifficulty();

    @Override
    public int getId() {
        return  901;
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
        return "AccessDifficulty";
    }
}
