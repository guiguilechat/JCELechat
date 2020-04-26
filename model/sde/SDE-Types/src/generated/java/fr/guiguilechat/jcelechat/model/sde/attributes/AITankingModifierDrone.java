package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Tanking modifier applied to drones if their owner is tanking. 1.0 is no modifier
 */
public class AITankingModifierDrone
    extends DoubleAttribute
{
    public static final AITankingModifierDrone INSTANCE = new AITankingModifierDrone();

    @Override
    public int getId() {
        return  1656;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.7;
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
        return "AITankingModifierDrone";
    }
}
