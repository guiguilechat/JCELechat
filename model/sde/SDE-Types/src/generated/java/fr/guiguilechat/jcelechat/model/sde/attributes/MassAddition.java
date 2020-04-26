package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Attribute for adding mass to a ship via an afterburner or MWD.
 */
public class MassAddition
    extends IntAttribute
{
    public static final MassAddition INSTANCE = new MassAddition();

    @Override
    public int getId() {
        return  796;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "MassAddition";
    }
}
