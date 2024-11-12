package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class DotMaxDamagePerTick
    extends IntAttribute
{
    public static final DotMaxDamagePerTick INSTANCE = new DotMaxDamagePerTick();

    @Override
    public int getId() {
        return  5736;
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
        return "DotMaxDamagePerTick";
    }
}
