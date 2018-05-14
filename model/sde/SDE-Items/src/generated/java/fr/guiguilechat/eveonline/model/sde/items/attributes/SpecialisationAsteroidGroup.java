package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The group at which the mining crystal is tuned to mine.
 */
public class SpecialisationAsteroidGroup
    extends IntAttribute
{
    public final static SpecialisationAsteroidGroup INSTANCE = new SpecialisationAsteroidGroup();

    @Override
    public int getId() {
        return  781;
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
