package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Can use cargo in space or not, 0 = no, 1 = yes
 */
public class CanUseCargoInSpace
    extends IntAttribute
{
    public final static CanUseCargoInSpace INSTANCE = new CanUseCargoInSpace();

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
}
