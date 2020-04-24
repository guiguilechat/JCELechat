package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Fall Off for NPC Target Paint
 */
public class EntityTargetPaintFallOff
    extends IntAttribute
{
    public static final EntityTargetPaintFallOff INSTANCE = new EntityTargetPaintFallOff();

    @Override
    public int getId() {
        return  954;
    }

    @Override
    public int getCatId() {
        return  21;
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
        return "EntityTargetPaintFallOff";
    }
}
