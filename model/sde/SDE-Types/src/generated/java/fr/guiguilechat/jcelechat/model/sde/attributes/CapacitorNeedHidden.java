package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * capacitorNeedHidden
 */
public class CapacitorNeedHidden
    extends IntAttribute
{
    public static final CapacitorNeedHidden INSTANCE = new CapacitorNeedHidden();

    @Override
    public int getId() {
        return  1319;
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
        return "CapacitorNeedHidden";
    }
}
