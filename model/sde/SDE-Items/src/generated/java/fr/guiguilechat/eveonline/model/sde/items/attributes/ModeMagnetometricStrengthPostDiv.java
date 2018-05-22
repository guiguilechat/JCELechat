package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class ModeMagnetometricStrengthPostDiv
    extends DoubleAttribute
{
    public final static ModeMagnetometricStrengthPostDiv INSTANCE = new ModeMagnetometricStrengthPostDiv();

    @Override
    public int getId() {
        return  1996;
    }

    @Override
    public int getCatId() {
        return  6;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ModeMagnetometricStrengthPostDiv";
    }
}
