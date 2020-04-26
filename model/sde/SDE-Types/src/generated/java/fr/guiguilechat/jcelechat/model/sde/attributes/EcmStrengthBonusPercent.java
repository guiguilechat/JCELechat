package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class EcmStrengthBonusPercent
    extends IntAttribute
{
    public static final EcmStrengthBonusPercent INSTANCE = new EcmStrengthBonusPercent();

    @Override
    public int getId() {
        return  1130;
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
        return "EcmStrengthBonusPercent";
    }
}
