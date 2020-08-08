package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BoosterTurretFalloffPenalty
    extends IntAttribute
{
    public static final BoosterTurretFalloffPenalty INSTANCE = new BoosterTurretFalloffPenalty();

    @Override
    public int getId() {
        return  1146;
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
        return "BoosterTurretFalloffPenalty";
    }
}
