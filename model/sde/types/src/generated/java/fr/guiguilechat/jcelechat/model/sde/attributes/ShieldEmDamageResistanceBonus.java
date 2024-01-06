package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * EM resistance bonus for shields
 */
public class ShieldEmDamageResistanceBonus
    extends IntAttribute
{
    public static final ShieldEmDamageResistanceBonus INSTANCE = new ShieldEmDamageResistanceBonus();

    @Override
    public int getId() {
        return  1489;
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
        return "ShieldEmDamageResistanceBonus";
    }
}
