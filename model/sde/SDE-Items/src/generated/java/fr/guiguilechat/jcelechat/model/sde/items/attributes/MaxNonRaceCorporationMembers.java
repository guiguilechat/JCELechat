package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Max non  race corporationMembers
 */
public class MaxNonRaceCorporationMembers
    extends IntAttribute
{
    public final static MaxNonRaceCorporationMembers INSTANCE = new MaxNonRaceCorporationMembers();

    @Override
    public int getId() {
        return  417;
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

    @Override
    public String toString() {
        return "MaxNonRaceCorporationMembers";
    }
}
