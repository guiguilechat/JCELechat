package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Increase in Small Projectile Turret optimal range
 */
public class RookieSPTOptimal
    extends DoubleAttribute
{
    public static final RookieSPTOptimal INSTANCE = new RookieSPTOptimal();

    @Override
    public int getId() {
        return  1869;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RookieSPTOptimal";
    }
}
