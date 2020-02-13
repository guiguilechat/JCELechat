package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Increases max velocity of all drone types.
 */
public class DroneMaxVelocityBonus
    extends IntAttribute
{
    public static final DroneMaxVelocityBonus INSTANCE = new DroneMaxVelocityBonus();

    @Override
    public int getId() {
        return  591;
    }

    @Override
    public int getCatId() {
        return  10;
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
        return "DroneMaxVelocityBonus";
    }
}
