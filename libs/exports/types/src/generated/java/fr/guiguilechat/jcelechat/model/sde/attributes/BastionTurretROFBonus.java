package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bastion effect bonus to turret rate of fire
 */
public class BastionTurretROFBonus
    extends IntAttribute
{
    public static final BastionTurretROFBonus INSTANCE = new BastionTurretROFBonus();

    @Override
    public int getId() {
        return  3109;
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
        return "BastionTurretROFBonus";
    }
}
