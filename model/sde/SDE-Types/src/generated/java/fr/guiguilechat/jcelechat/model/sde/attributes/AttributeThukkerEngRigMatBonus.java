package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class AttributeThukkerEngRigMatBonus
    extends DoubleAttribute
{
    public static final AttributeThukkerEngRigMatBonus INSTANCE = new AttributeThukkerEngRigMatBonus();

    @Override
    public int getId() {
        return  2653;
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
        return "AttributeThukkerEngRigMatBonus";
    }
}
