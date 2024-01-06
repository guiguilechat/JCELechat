package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Turret Damage Bonus Percentage
 */
public class SiegeTurretDamageBonus
    extends IntAttribute
{
    public static final SiegeTurretDamageBonus INSTANCE = new SiegeTurretDamageBonus();

    @Override
    public int getId() {
        return  2307;
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
        return "SiegeTurretDamageBonus";
    }
}
