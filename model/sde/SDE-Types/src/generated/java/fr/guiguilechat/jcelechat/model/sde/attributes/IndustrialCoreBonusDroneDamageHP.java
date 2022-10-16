package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class IndustrialCoreBonusDroneDamageHP
    extends IntAttribute
{
    public static final IndustrialCoreBonusDroneDamageHP INSTANCE = new IndustrialCoreBonusDroneDamageHP();

    @Override
    public int getId() {
        return  2583;
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
        return "IndustrialCoreBonusDroneDamageHP";
    }
}
