package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


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
        return "SiegeMissileDamageBonus";
    }
}
