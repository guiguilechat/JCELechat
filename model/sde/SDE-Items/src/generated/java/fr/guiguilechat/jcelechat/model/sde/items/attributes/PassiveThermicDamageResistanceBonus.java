package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class PassiveThermicDamageResistanceBonus
    extends IntAttribute
{
    public final static PassiveThermicDamageResistanceBonus INSTANCE = new PassiveThermicDamageResistanceBonus();

    @Override
    public int getId() {
        return  997;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "PassiveThermicDamageResistanceBonus";
    }
}
