package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipRoleBonusOreMiningDuration
    extends IntAttribute
{
    public static final ShipRoleBonusOreMiningDuration INSTANCE = new ShipRoleBonusOreMiningDuration();

    @Override
    public int getId() {
        return  3230;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
        return "ShipRoleBonusOreMiningDuration";
    }
}
