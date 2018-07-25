package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Whether this tool causes damage to crystals with each use of them.
 */
public class CrystalsGetDamaged
    extends IntAttribute
{
    public final static CrystalsGetDamaged INSTANCE = new CrystalsGetDamaged();

    @Override
    public int getId() {
        return  786;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "CrystalsGetDamaged";
    }
}