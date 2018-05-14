package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Increases max velocity of all drone types.
 */
public class DroneMaxVelocityBonus
    extends IntAttribute
{
    public final static DroneMaxVelocityBonus INSTANCE = new DroneMaxVelocityBonus();

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
}
