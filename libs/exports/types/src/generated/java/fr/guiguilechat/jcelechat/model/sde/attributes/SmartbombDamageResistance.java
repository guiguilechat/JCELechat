package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


public class SmartbombDamageResistance
    extends RealAttribute
{
    public static final SmartbombDamageResistance INSTANCE = new SmartbombDamageResistance();

    @Override
    public int getId() {
        return  6184;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "SmartbombDamageResistance";
    }
}
