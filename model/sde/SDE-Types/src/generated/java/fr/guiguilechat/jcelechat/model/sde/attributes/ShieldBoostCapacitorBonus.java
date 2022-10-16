package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to capacitor need for shield boosters.
 */
public class ShieldBoostCapacitorBonus
    extends IntAttribute
{
    public static final ShieldBoostCapacitorBonus INSTANCE = new ShieldBoostCapacitorBonus();

    @Override
    public int getId() {
        return  851;
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
        return "ShieldBoostCapacitorBonus";
    }
}
