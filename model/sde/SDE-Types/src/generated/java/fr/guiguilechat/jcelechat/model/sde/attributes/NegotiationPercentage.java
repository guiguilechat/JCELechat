package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Percentage of pay from agent 
 */
public class NegotiationPercentage
    extends IntAttribute
{
    public static final NegotiationPercentage INSTANCE = new NegotiationPercentage();

    @Override
    public int getId() {
        return  355;
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
        return "NegotiationPercentage";
    }
}
