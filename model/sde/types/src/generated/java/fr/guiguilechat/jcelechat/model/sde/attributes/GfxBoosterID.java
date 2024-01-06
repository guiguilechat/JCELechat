package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Graphic ID of the boosters for drone type ships.
 */
public class GfxBoosterID
    extends IntAttribute
{
    public static final GfxBoosterID INSTANCE = new GfxBoosterID();

    @Override
    public int getId() {
        return  246;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "GfxBoosterID";
    }
}
