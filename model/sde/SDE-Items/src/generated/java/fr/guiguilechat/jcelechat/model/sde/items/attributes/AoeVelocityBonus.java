package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Increases velocity of missile explosion
 */
public class AoeVelocityBonus
    extends DoubleAttribute
{
    public final static AoeVelocityBonus INSTANCE = new AoeVelocityBonus();

    @Override
    public int getId() {
        return  847;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return "AoeVelocityBonus";
    }
}
