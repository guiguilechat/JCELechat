package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class BoosterTurretFalloffPenalty
    extends IntAttribute
{
    public final static BoosterTurretFalloffPenalty INSTANCE = new BoosterTurretFalloffPenalty();

    @Override
    public int getId() {
        return  1146;
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
        return "BoosterTurretFalloffPenalty";
    }
}
