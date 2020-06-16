package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum number of arc targets hit.
 */
public class VortonArcTargets
    extends IntAttribute
{
    public static final VortonArcTargets INSTANCE = new VortonArcTargets();

    @Override
    public int getId() {
        return  3037;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "VortonArcTargets";
    }
}
