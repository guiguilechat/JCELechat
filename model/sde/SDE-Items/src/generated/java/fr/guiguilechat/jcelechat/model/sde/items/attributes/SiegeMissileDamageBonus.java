package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Missile Damage Bonus Percentage
 */
public class SiegeMissileDamageBonus
    extends IntAttribute
{
    public static final SiegeMissileDamageBonus INSTANCE = new SiegeMissileDamageBonus();

    @Override
    public int getId() {
        return  2306;
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
        return "SiegeMissileDamageBonus";
    }
}
