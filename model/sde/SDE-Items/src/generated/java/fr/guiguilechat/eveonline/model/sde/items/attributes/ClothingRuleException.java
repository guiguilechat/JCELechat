package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * When evaluating if the character is dressed well enough, this item will be evaluated as it's not equiped
 */
public class ClothingRuleException
    extends IntAttribute
{
    public final static ClothingRuleException INSTANCE = new ClothingRuleException();

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
}
