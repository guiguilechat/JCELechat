package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Torpedo Velocity Bonus Percentage
 */
public class SiegeTorpedoVelocityBonus
    extends IntAttribute
{
    public static final SiegeTorpedoVelocityBonus INSTANCE = new SiegeTorpedoVelocityBonus();

    @Override
    public int getId() {
        return  2304;
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
        return "SiegeTorpedoVelocityBonus";
    }
}
