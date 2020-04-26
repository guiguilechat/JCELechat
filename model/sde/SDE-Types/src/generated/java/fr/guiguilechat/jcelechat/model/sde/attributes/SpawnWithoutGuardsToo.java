package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Whether a spawn container should refill itself when there are no guards assigned to it.
 */
public class SpawnWithoutGuardsToo
    extends IntAttribute
{
    public static final SpawnWithoutGuardsToo INSTANCE = new SpawnWithoutGuardsToo();

    @Override
    public int getId() {
        return  903;
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
        return "SpawnWithoutGuardsToo";
    }
}
