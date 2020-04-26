package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Bonus on Material decrease for engineering rigs
 */
public class AttributeEngRigMatBonus
    extends DoubleAttribute
{
    public static final AttributeEngRigMatBonus INSTANCE = new AttributeEngRigMatBonus();

    @Override
    public int getId() {
        return  2594;
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
