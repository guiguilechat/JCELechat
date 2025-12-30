package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class DotMaxDamagePerTickBonus
    extends IntAttribute
{
    public static final DotMaxDamagePerTickBonus INSTANCE = new DotMaxDamagePerTickBonus();

    @Override
    public int getId() {
        return  5750;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "DotMaxDamagePerTickBonus";
    }
}
