package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * The amount the yield is modified when mining the asteroid group this crystal is tuned for.
 */
public class SpecialisationAsteroidYieldMultiplier
    extends DoubleAttribute
{
    public final static SpecialisationAsteroidYieldMultiplier INSTANCE = new SpecialisationAsteroidYieldMultiplier();

    @Override
    public int getId() {
        return  782;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
