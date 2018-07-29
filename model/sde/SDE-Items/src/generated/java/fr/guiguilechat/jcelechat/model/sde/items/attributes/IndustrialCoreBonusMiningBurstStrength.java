package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class IndustrialCoreBonusMiningBurstStrength
    extends IntAttribute
{
    public final static IndustrialCoreBonusMiningBurstStrength INSTANCE = new IndustrialCoreBonusMiningBurstStrength();

    @Override
    public int getId() {
        return  2587;
    }

    @Override
    public int getCatId() {
        return  37;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return true;
    }

    @Override
    public String toString() {
        return "IndustrialCoreBonusMiningBurstStrength";
    }
}
