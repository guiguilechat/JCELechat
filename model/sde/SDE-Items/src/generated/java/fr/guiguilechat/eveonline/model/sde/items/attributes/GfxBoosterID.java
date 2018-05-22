package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Graphic ID of the boosters for drone type ships.
 */
public class GfxBoosterID
    extends IntAttribute
{
    public final static GfxBoosterID INSTANCE = new GfxBoosterID();

    @Override
    public int getId() {
        return  246;
    }

    @Override
    public int getCatId() {
        return  31;
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
