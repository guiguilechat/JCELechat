package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of Light Fighters the structure can launch.
 */
public class FighterStandupLightSlots
    extends IntAttribute
{
    public static final FighterStandupLightSlots INSTANCE = new FighterStandupLightSlots();

    @Override
    public int getId() {
        return  2737;
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
        return "FighterStandupLightSlots";
    }
}
