package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The % of industrial assembly cost a player has to pay to assemble a industrial
 */
public class IndustrialConstructionCost
    extends IntAttribute
{
    public static final IndustrialConstructionCost INSTANCE = new IndustrialConstructionCost();

    @Override
    public int getId() {
        return  392;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return "IndustrialConstructionCost";
    }
}
