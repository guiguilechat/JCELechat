package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus to signatureRadiusBonus
 */
public class SignatureRadiusBonusBonus
    extends IntAttribute
{
    public final static SignatureRadiusBonusBonus INSTANCE = new SignatureRadiusBonusBonus();

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
