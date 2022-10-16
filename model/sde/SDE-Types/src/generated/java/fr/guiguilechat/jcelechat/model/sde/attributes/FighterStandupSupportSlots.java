package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of Support Fighters the structure can launch.
 */
public class FighterStandupSupportSlots
    extends IntAttribute
{
    public static final FighterStandupSupportSlots INSTANCE = new FighterStandupSupportSlots();

    @Override
    public int getId() {
        return  2738;
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
        return "FighterStandupSupportSlots";
    }
}
