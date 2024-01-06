package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bastion effect bonus to missile rate of fire
 */
public class BastionMissileROFBonus
    extends IntAttribute
{
    public static final BastionMissileROFBonus INSTANCE = new BastionMissileROFBonus();

    @Override
    public int getId() {
        return  3108;
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
        return "BastionMissileROFBonus";
    }
}
