package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The % of industrial assembly cost a player has to pay to assemble a industrial
 */
public class IndustrialConstructionCost
    extends IntAttribute
{
    public final static IndustrialConstructionCost INSTANCE = new IndustrialConstructionCost();

    @Override
    public int getId() {
        return  392;
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
}
