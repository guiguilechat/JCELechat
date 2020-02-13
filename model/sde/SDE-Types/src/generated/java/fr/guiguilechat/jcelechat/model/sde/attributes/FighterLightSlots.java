package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of Light Fighters the ship can launch.
 */
public class FighterLightSlots
    extends IntAttribute
{
    public static final FighterLightSlots INSTANCE = new FighterLightSlots();

    @Override
    public int getId() {
        return  2217;
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
        return "FighterLightSlots";
    }
}
