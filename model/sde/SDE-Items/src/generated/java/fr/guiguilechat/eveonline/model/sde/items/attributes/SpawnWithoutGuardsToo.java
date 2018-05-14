package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Whether a spawn container should refill itself when there are no guards assigned to it.
 */
public class SpawnWithoutGuardsToo
    extends IntAttribute
{
    public final static SpawnWithoutGuardsToo INSTANCE = new SpawnWithoutGuardsToo();

    @Override
    public int getId() {
        return  903;
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
}
