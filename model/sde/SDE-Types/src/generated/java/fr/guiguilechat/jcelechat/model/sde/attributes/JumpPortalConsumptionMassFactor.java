package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier used to calculate amount of quantity used for jumping via portals based on mass of ship.
 */
public class JumpPortalConsumptionMassFactor
    extends DoubleAttribute
{
    public static final JumpPortalConsumptionMassFactor INSTANCE = new JumpPortalConsumptionMassFactor();

    @Override
    public int getId() {
        return  1001;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "JumpPortalConsumptionMassFactor";
    }
}
