package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The number of utility element slots a virus has.
 */
public class VirusElementSlots
    extends IntAttribute
{
    public static final VirusElementSlots INSTANCE = new VirusElementSlots();

    @Override
    public int getId() {
        return  1911;
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
        return "VirusElementSlots";
    }
}
