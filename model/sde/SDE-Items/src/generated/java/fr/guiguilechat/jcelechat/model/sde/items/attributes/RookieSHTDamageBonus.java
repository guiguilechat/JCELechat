package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to Small Hybrid Turret damage
 */
public class RookieSHTDamageBonus
    extends IntAttribute
{
    public final static RookieSHTDamageBonus INSTANCE = new RookieSHTDamageBonus();

    @Override
    public int getId() {
        return  1830;
    }

    @Override
    public int getCatId() {
        return  0;
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
        return "RookieSHTDamageBonus";
    }
}
