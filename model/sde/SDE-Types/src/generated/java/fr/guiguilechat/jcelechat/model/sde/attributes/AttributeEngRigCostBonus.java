package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus on Cost decrease for engineering rigs
 */
public class AttributeEngRigCostBonus
    extends IntAttribute
{
    public static final AttributeEngRigCostBonus INSTANCE = new AttributeEngRigCostBonus();

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

    @Override
    public String toString() {
        return "AttributeEngRigCostBonus";
    }
}
