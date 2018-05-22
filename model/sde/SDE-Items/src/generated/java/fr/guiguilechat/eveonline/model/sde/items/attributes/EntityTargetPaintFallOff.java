package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Fall Off for NPC Target Paint
 */
public class EntityTargetPaintFallOff
    extends IntAttribute
{
    public final static EntityTargetPaintFallOff INSTANCE = new EntityTargetPaintFallOff();

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
