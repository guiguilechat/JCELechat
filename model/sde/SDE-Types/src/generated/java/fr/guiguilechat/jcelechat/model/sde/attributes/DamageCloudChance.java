package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * %chance of new asteroid releasing damage cloud each mining turn.
 */
public class DamageCloudChance
    extends DoubleAttribute
{
    public static final DamageCloudChance INSTANCE = new DamageCloudChance();

    @Override
    public int getId() {
        return  522;
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
        return "DamageCloudChance";
    }
}
