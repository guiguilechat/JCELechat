package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Set's whether or not a structure will automatically repair itself.
 */
public class CanHaveAutoRepair
    extends IntAttribute
{
    public static final CanHaveAutoRepair INSTANCE = new CanHaveAutoRepair();

    @Override
    public int getId() {
        return  5770;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "CanHaveAutoRepair";
    }
}
