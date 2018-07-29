package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Turret Damage Bonus Percentage
 */
public class SiegeTurretDamageBonus
    extends IntAttribute
{
    public final static SiegeTurretDamageBonus INSTANCE = new SiegeTurretDamageBonus();

    @Override
    public int getId() {
        return  2307;
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
        return "SiegeTurretDamageBonus";
    }
}
