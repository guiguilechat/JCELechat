package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Can use cargo in space or not, 0 = no, 1 = yes
 */
public class CanUseCargoInSpace
    extends IntAttribute
{
    public static final CanUseCargoInSpace INSTANCE = new CanUseCargoInSpace();

    @Override
    public int getId() {
        return  849;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "CanUseCargoInSpace";
    }
}
