package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class AttributeThukkerEngRigMatBonus
    extends RealAttribute
{
    public static final AttributeThukkerEngRigMatBonus INSTANCE = new AttributeThukkerEngRigMatBonus();

    @Override
    public int getId() {
        return  2653;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
