package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Autogenerated skill attribute, armorHpBonus
 */
public class ArmorHpBonus
    extends IntAttribute
{
    public static final ArmorHpBonus INSTANCE = new ArmorHpBonus();

    @Override
    public int getId() {
        return  335;
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
        return "ArmorHpBonus";
    }
}