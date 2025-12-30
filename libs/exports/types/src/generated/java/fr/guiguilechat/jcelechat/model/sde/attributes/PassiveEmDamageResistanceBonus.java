package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class PassiveEmDamageResistanceBonus
    extends IntAttribute
{
    public static final PassiveEmDamageResistanceBonus INSTANCE = new PassiveEmDamageResistanceBonus();

    @Override
    public int getId() {
        return  994;
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
        return "PassiveEmDamageResistanceBonus";
    }
}
