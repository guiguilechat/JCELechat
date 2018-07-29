package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
 */
public class IndustryBlueprintRank
    extends DoubleAttribute
{
    public final static IndustryBlueprintRank INSTANCE = new IndustryBlueprintRank();

    @Override
    public int getId() {
        return  1955;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "IndustryBlueprintRank";
    }
}
