package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Autogenerated skill attribute, TurretSpeeBonus
 */
public class TurretSpeeBonus
    extends IntAttribute
{
    public static final TurretSpeeBonus INSTANCE = new TurretSpeeBonus();

    @Override
    public int getId() {
        return  441;
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
        return "TurretSpeeBonus";
    }
}
