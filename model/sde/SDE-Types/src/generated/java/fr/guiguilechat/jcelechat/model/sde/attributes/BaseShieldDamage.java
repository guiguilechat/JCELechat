package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Just for the UI to display base damage on shield.
 */
public class BaseShieldDamage
    extends IntAttribute
{
    public static final BaseShieldDamage INSTANCE = new BaseShieldDamage();

    @Override
    public int getId() {
        return  612;
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
        return "BaseShieldDamage";
    }
}
