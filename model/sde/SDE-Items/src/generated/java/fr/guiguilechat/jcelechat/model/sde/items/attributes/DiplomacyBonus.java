package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to Effective Standing towards Hostile NPC
 */
public class DiplomacyBonus
    extends IntAttribute
{
    public static final DiplomacyBonus INSTANCE = new DiplomacyBonus();

    @Override
    public int getId() {
        return  356;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "DiplomacyBonus";
    }
}
