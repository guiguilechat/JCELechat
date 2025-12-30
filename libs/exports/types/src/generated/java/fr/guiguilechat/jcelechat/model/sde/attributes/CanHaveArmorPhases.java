package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Can a structure have armor phases
 */
public class CanHaveArmorPhases
    extends IntAttribute
{
    public static final CanHaveArmorPhases INSTANCE = new CanHaveArmorPhases();

    @Override
    public int getId() {
        return  5771;
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
        return "CanHaveArmorPhases";
    }
}
