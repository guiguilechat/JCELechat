package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class EntosisCPUPenalty
    extends IntAttribute
{
    public static final EntosisCPUPenalty INSTANCE = new EntosisCPUPenalty();

    @Override
    public int getId() {
        return  2042;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EntosisCPUPenalty";
    }
}
