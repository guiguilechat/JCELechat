package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The amount the yield is modified when mining the asteroid group this crystal is tuned for.
 */
public class SpecialisationAsteroidYieldMultiplier
    extends RealAttribute
{
    public static final SpecialisationAsteroidYieldMultiplier INSTANCE = new SpecialisationAsteroidYieldMultiplier();

    @Override
    public int getId() {
        return  782;
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
        return "SpecialisationAsteroidYieldMultiplier";
    }
}
