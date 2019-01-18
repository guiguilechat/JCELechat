package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Adds to the virus coherence of profession modules
 */
public class VirusCoherenceBonus
    extends IntAttribute
{
    public static final VirusCoherenceBonus INSTANCE = new VirusCoherenceBonus();

    @Override
    public int getId() {
        return  1915;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "VirusCoherenceBonus";
    }
}
