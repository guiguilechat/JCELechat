package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus on Cost decrease for engineering rigs
 */
public class AttributeEngRigCostBonus
    extends IntAttribute
{
    public final static AttributeEngRigCostBonus INSTANCE = new AttributeEngRigCostBonus();

    @Override
    public int getId() {
        return  2595;
    }

    @Override
    public int getCatId() {
        return  37;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
