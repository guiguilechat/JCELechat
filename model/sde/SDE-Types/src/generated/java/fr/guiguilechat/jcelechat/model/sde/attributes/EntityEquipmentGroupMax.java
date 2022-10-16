package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum drops of same group (example: entity can only drop 1 of group: energy laser)
 */
public class EntityEquipmentGroupMax
    extends IntAttribute
{
    public static final EntityEquipmentGroupMax INSTANCE = new EntityEquipmentGroupMax();

    @Override
    public int getId() {
        return  465;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "EntityEquipmentGroupMax";
    }
}
