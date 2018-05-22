package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Multiplier to missiles ability to hit fast targets
 */
public class AoeVelocityMultiplier
    extends DoubleAttribute
{
    public final static AoeVelocityMultiplier INSTANCE = new AoeVelocityMultiplier();

    @Override
    public int getId() {
        return  1483;
    }

    @Override
    public int getCatId() {
        return  1;
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
        return "AoeVelocityMultiplier";
    }
}
