package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Amount of armor healed by structure repair effects
 */
public class StructureArmorRepairAmount
    extends IntAttribute
{
    public final static StructureArmorRepairAmount INSTANCE = new StructureArmorRepairAmount();

    @Override
    public int getId() {
        return  1170;
    }

    @Override
    public int getCatId() {
        return  7;
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
}
