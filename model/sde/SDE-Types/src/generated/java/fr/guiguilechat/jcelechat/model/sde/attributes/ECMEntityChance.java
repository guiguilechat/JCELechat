package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Chance of NPC effect to be activated each duration
 */
public class ECMEntityChance
    extends DoubleAttribute
{
    public static final ECMEntityChance INSTANCE = new ECMEntityChance();

    @Override
    public int getId() {
        return  930;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ECMEntityChance";
    }
}
