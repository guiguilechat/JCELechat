package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


public class HydraMissileExplosionVelocityBonus
    extends DoubleAttribute
{
    public static final HydraMissileExplosionVelocityBonus INSTANCE = new HydraMissileExplosionVelocityBonus();

    @Override
    public int getId() {
        return  3031;
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
        return "HydraMissileExplosionVelocityBonus";
    }
}
