package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BoosterShieldCapacityPenalty
    extends IntAttribute
{
    public static final BoosterShieldCapacityPenalty INSTANCE = new BoosterShieldCapacityPenalty();

    @Override
    public int getId() {
        return  1143;
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
        return "BoosterShieldCapacityPenalty";
    }
}
