package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Length of Immobility time.
 */
public class DoomsdayImmobilityDuration
    extends IntAttribute
{
    public static final DoomsdayImmobilityDuration INSTANCE = new DoomsdayImmobilityDuration();

    @Override
    public int getId() {
        return  2428;
    }

    @Override
    public int getCatId() {
        return  39;
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
        return "DoomsdayImmobilityDuration";
    }
}
