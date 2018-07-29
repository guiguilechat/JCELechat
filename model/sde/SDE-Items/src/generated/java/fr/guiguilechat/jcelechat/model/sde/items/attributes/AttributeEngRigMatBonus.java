package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Bonus on Material decrease for engineering rigs
 */
public class AttributeEngRigMatBonus
    extends DoubleAttribute
{
    public final static AttributeEngRigMatBonus INSTANCE = new AttributeEngRigMatBonus();

    @Override
    public int getId() {
        return  2594;
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

    @Override
    public String toString() {
        return "AttributeEngRigMatBonus";
    }
}
