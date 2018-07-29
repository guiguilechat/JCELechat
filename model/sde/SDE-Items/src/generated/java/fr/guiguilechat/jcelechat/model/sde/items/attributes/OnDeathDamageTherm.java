package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Thermal Damage when the ship dies
 */
public class OnDeathDamageTherm
    extends IntAttribute
{
    public final static OnDeathDamageTherm INSTANCE = new OnDeathDamageTherm();

    @Override
    public int getId() {
        return  2272;
    }

    @Override
    public int getCatId() {
        return  41;
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
        return "OnDeathDamageTherm";
    }
}
