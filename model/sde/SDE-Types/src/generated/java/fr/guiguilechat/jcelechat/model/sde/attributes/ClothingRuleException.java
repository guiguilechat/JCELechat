package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * When evaluating if the character is dressed well enough, this item will be evaluated as it's not equiped
 */
public class ClothingRuleException
    extends IntAttribute
{
    public static final ClothingRuleException INSTANCE = new ClothingRuleException();

    @Override
    public int getId() {
        return  1957;
    }

    @Override
    public int getCatId() {
        return  0;
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
        return "ClothingRuleException";
    }
}
