package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Length of Immobility time.
 */
public class DoomsdayImmobilityDuration
    extends IntAttribute
{
    public final static DoomsdayImmobilityDuration INSTANCE = new DoomsdayImmobilityDuration();

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
