package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Number of Light Fighters the structure can launch.
 */
public class FighterStandupLightSlots
    extends IntAttribute
{
    public final static FighterStandupLightSlots INSTANCE = new FighterStandupLightSlots();

    @Override
    public int getId() {
        return  2737;
    }

    @Override
    public int getCatId() {
        return  38;
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
