package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The ID of a typelist of asteroid typeIDs that a mining crystal can affect
 */
public class SpecializationAsteroidTypeList
    extends IntAttribute
{
    public static final SpecializationAsteroidTypeList INSTANCE = new SpecializationAsteroidTypeList();

    @Override
    public int getId() {
        return  3148;
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
        return "SpecializationAsteroidTypeList";
    }
}
