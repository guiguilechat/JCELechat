package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class RoleBonusCBC2
    extends IntAttribute
{
    public static final RoleBonusCBC2 INSTANCE = new RoleBonusCBC2();

    @Override
    public int getId() {
        return  5045;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RoleBonusCBC2";
    }
}
