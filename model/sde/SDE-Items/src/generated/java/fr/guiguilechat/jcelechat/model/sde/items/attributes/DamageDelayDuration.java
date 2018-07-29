package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The delay in ms until the damage is done to the target. (Allows some FX to be played)
 */
public class DamageDelayDuration
    extends IntAttribute
{
    public final static DamageDelayDuration INSTANCE = new DamageDelayDuration();

    @Override
    public int getId() {
        return  1839;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  10000.0;
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
        return "DamageDelayDuration";
    }
}
