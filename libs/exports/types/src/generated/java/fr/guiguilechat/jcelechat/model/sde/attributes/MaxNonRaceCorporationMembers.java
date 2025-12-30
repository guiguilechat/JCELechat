package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Max non  race corporationMembers
 */
public class MaxNonRaceCorporationMembers
    extends IntAttribute
{
    public static final MaxNonRaceCorporationMembers INSTANCE = new MaxNonRaceCorporationMembers();

    @Override
    public int getId() {
        return  417;
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
        return "MaxNonRaceCorporationMembers";
    }
}
