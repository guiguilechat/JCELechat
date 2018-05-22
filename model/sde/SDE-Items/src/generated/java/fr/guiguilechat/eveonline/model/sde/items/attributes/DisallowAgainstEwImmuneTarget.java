package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * When set to non-zero on a module with an assistance effect, the module cannot be activated against a target that has a positive disallowOffensiveModifiers attribute
 */
public class DisallowAgainstEwImmuneTarget
    extends IntAttribute
{
    public final static DisallowAgainstEwImmuneTarget INSTANCE = new DisallowAgainstEwImmuneTarget();

    @Override
    public int getId() {
        return  1798;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "DisallowAgainstEwImmuneTarget";
    }
}
