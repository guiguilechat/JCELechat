package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
 * The amount of starting shield capacity of the NPC.
 */
public class ShieldCharge
    extends DoubleAttribute
{
    public static final ShieldCharge INSTANCE = new ShieldCharge();

    @Override
    public int getId() {
        return  264;
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
        return "ShieldCharge";
    }
}
