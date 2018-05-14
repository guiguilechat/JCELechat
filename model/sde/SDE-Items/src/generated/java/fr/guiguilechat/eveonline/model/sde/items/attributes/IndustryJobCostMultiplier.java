package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Multiplies the job cost for this blueprint type by the specified value
 */
public class IndustryJobCostMultiplier
    extends IntAttribute
{
    public final static IndustryJobCostMultiplier INSTANCE = new IndustryJobCostMultiplier();

    @Override
    public int getId() {
        return  1954;
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
        return  1.0;
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
