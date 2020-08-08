package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Graphic ID of the turrets for drone type ships.
 */
public class GfxTurretID
    extends IntAttribute
{
    public static final GfxTurretID INSTANCE = new GfxTurretID();

    @Override
    public int getId() {
        return  245;
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
        return "GfxTurretID";
    }
}
