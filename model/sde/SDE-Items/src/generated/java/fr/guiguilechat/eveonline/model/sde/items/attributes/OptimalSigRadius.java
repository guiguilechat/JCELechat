package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Prefered target signature. The base signature radius at which the turret's tracking speed is rated. 
 */
public class OptimalSigRadius
    extends IntAttribute
{
    public final static OptimalSigRadius INSTANCE = new OptimalSigRadius();

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
