package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class RoleBonusTractorBeamVelocity
    extends IntAttribute
{
    public final static RoleBonusTractorBeamVelocity INSTANCE = new RoleBonusTractorBeamVelocity();

    @Override
    public int getId() {
        return  1357;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RoleBonusTractorBeamVelocity";
    }
}
