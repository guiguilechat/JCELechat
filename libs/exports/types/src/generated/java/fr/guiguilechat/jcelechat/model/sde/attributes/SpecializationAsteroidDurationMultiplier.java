package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The amount the mining duration is modified when mining the asteroid group this crystal is tuned for.
 */
public class SpecializationAsteroidDurationMultiplier
    extends RealAttribute
{
    public static final SpecializationAsteroidDurationMultiplier INSTANCE = new SpecializationAsteroidDurationMultiplier();

    @Override
    public int getId() {
        return  3161;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "SpecializationAsteroidDurationMultiplier";
    }
}
