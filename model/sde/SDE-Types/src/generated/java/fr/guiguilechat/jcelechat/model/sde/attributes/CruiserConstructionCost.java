package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The % of cruiser assembly cost a player has to pay to assemble a cruiser
 */
public class CruiserConstructionCost
    extends IntAttribute
{
    public static final CruiserConstructionCost INSTANCE = new CruiserConstructionCost();

    @Override
    public int getId() {
        return  389;
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
        return "CruiserConstructionCost";
    }
}
