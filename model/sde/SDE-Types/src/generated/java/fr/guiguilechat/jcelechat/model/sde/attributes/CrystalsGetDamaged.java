package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Whether this tool causes damage to crystals with each use of them.
 */
public class CrystalsGetDamaged
    extends IntAttribute
{
    public static final CrystalsGetDamaged INSTANCE = new CrystalsGetDamaged();

    @Override
    public int getId() {
        return  786;
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
