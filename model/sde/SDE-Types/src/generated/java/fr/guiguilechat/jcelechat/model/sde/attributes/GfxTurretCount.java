package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of Turrets to fit for entity type ships
 */
public class GfxTurretCount
    extends IntAttribute
{
    public static final GfxTurretCount INSTANCE = new GfxTurretCount();

    @Override
    public int getId() {
        return  2654;
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
        return "GfxTurretCount";
    }
}
