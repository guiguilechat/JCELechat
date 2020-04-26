package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of Heavy Fighters the structure can launch.
 */
public class FighterStandupHeavySlots
    extends IntAttribute
{
    public static final FighterStandupHeavySlots INSTANCE = new FighterStandupHeavySlots();

    @Override
    public int getId() {
        return  2739;
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
        return "FighterStandupHeavySlots";
    }
}
