package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of Support Fighters the ship can launch.
 */
public class FighterSupportSlots
    extends IntAttribute
{
    public static final FighterSupportSlots INSTANCE = new FighterSupportSlots();

    @Override
    public int getId() {
        return  2218;
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
        return "FighterSupportSlots";
    }
}
