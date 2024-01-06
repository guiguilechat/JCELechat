package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus on Time decrease for engineering rigs
 */
public class AttributeEngRigTimeBonus
    extends IntAttribute
{
    public static final AttributeEngRigTimeBonus INSTANCE = new AttributeEngRigTimeBonus();

    @Override
    public int getId() {
        return  2593;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
        return "AttributeEngRigTimeBonus";
    }
}
