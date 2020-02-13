package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to signatureRadiusBonus
 */
public class SignatureRadiusBonusBonus
    extends IntAttribute
{
    public static final SignatureRadiusBonusBonus INSTANCE = new SignatureRadiusBonusBonus();

    @Override
    public int getId() {
        return  1227;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "SignatureRadiusBonusBonus";
    }
}
