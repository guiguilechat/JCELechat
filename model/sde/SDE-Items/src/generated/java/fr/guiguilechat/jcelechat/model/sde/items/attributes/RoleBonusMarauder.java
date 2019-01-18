package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


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
