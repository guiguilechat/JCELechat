package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Mass multiplier
 */
public class SiegeMassMultiplier
    extends IntAttribute
{
    public static final SiegeMassMultiplier INSTANCE = new SiegeMassMultiplier();

    @Override
    public int getId() {
        return  1471;
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
        return "SiegeMassMultiplier";
    }
}
