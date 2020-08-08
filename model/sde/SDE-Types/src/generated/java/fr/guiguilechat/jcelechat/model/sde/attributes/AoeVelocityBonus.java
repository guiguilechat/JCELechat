package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Increases velocity of missile explosion
 */
public class AoeVelocityBonus
    extends DoubleAttribute
{
    public static final AoeVelocityBonus INSTANCE = new AoeVelocityBonus();

    @Override
    public int getId() {
        return  847;
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
        return "AoeVelocityBonus";
    }
}
