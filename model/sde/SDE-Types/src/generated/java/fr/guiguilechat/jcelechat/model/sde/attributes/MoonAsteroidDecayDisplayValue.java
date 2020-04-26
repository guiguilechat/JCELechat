package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This attribute doesn't directly impact the asteroid decay, but is used to expose the decay time to the show-info window
 */
public class MoonAsteroidDecayDisplayValue
    extends IntAttribute
{
    public static final MoonAsteroidDecayDisplayValue INSTANCE = new MoonAsteroidDecayDisplayValue();

    @Override
    public int getId() {
        return  2728;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  48.0;
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
        return "MoonAsteroidDecayDisplayValue";
    }
}
