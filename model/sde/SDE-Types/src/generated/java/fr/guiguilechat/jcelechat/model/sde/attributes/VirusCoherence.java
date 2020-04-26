package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The coherence of a virus.
 */
public class VirusCoherence
    extends IntAttribute
{
    public static final VirusCoherence INSTANCE = new VirusCoherence();

    @Override
    public int getId() {
        return  1909;
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
        return "VirusCoherence";
    }
}
