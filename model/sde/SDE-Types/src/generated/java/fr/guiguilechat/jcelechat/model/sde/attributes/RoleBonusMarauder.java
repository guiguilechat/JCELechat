package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Role bonus for Marauders.
 */
public class RoleBonusMarauder
    extends IntAttribute
{
    public static final RoleBonusMarauder INSTANCE = new RoleBonusMarauder();

    @Override
    public int getId() {
        return  1923;
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
        return "RoleBonusMarauder";
    }
}
