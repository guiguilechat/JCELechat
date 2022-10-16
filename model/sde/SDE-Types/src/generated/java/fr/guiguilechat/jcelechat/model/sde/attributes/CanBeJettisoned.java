package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This attribute is not needed by default. You only need to add it if you want to stop something from being jettisoned.
 * 
 * The primary case for this was Station Containers.
 * 
 *  0 = Cannot be jettisoned.
 *  1 = Can be jettisoned.
 */
public class CanBeJettisoned
    extends IntAttribute
{
    public static final CanBeJettisoned INSTANCE = new CanBeJettisoned();

    @Override
    public int getId() {
        return  1852;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "CanBeJettisoned";
    }
}
