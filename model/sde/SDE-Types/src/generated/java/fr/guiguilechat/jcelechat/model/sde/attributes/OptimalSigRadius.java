package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Prefered target signature. The base signature radius at which the turret's tracking speed is rated. 
 */
public class OptimalSigRadius
    extends IntAttribute
{
    public static final OptimalSigRadius INSTANCE = new OptimalSigRadius();

    @Override
    public int getId() {
        return  620;
    }

    @Override
    public int getCatId() {
        return  29;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1000.0;
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
        return "OptimalSigRadius";
    }
}
