package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class VelocityPenaltyReduction
    extends IntAttribute
{
    public static final VelocityPenaltyReduction INSTANCE = new VelocityPenaltyReduction();

    @Override
    public int getId() {
        return  2102;
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
        return "VelocityPenaltyReduction";
    }
}
