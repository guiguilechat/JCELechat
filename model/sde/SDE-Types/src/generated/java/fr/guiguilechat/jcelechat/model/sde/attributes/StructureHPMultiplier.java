package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier to the ships structural HP.
 */
public class StructureHPMultiplier
    extends DoubleAttribute
{
    public static final StructureHPMultiplier INSTANCE = new StructureHPMultiplier();

    @Override
    public int getId() {
        return  150;
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
        return "StructureHPMultiplier";
    }
}
