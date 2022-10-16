package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * When set to non-zero on a module with an assistance effect, the module cannot be activated against a target that has a positive disallowOffensiveModifiers attribute
 */
public class DisallowAgainstEwImmuneTarget
    extends IntAttribute
{
    public static final DisallowAgainstEwImmuneTarget INSTANCE = new DisallowAgainstEwImmuneTarget();

    @Override
    public int getId() {
        return  1798;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
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
        return "DisallowAgainstEwImmuneTarget";
    }
}
