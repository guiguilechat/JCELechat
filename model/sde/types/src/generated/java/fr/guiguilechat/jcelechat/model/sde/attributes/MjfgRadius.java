package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * range effected by mjfg scoop
 */
public class MjfgRadius
    extends IntAttribute
{
    public static final MjfgRadius INSTANCE = new MjfgRadius();

    @Override
    public int getId() {
        return  2067;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "MjfgRadius";
    }
}
