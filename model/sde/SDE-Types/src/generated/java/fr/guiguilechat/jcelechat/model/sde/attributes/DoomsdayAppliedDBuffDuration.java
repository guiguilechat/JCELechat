package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Length of time for applied debuffs to persist on a target
 */
public class DoomsdayAppliedDBuffDuration
    extends IntAttribute
{
    public static final DoomsdayAppliedDBuffDuration INSTANCE = new DoomsdayAppliedDBuffDuration();

    @Override
    public int getId() {
        return  5412;
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
        return "DoomsdayAppliedDBuffDuration";
    }
}
