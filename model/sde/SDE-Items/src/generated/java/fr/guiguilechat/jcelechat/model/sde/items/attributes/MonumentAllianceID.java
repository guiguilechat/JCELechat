package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * This is used to connect the alliance logos to the monuments that were placed as part of the outpost and conquerable station phaseout process in 2018
 */
public class MonumentAllianceID
    extends IntAttribute
{
    public final static MonumentAllianceID INSTANCE = new MonumentAllianceID();

    @Override
    public int getId() {
        return  2787;
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
        return "MonumentAllianceID";
    }
}
