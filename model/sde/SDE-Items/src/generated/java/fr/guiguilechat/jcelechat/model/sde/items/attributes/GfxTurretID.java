package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Graphic ID of the turrets for drone type ships.
 */
public class GfxTurretID
    extends IntAttribute
{
    public final static GfxTurretID INSTANCE = new GfxTurretID();

    @Override
    public int getId() {
        return  245;
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
        return "GfxTurretID";
    }
}
