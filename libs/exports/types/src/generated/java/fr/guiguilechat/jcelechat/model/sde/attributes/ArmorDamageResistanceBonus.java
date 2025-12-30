package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ArmorDamageResistanceBonus
    extends IntAttribute
{
    public static final ArmorDamageResistanceBonus INSTANCE = new ArmorDamageResistanceBonus();

    @Override
    public int getId() {
        return  3429;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "ArmorDamageResistanceBonus";
    }
}
