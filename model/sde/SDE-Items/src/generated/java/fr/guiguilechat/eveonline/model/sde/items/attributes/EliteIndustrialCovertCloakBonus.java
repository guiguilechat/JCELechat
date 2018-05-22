package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class EliteIndustrialCovertCloakBonus
    extends IntAttribute
{
    public final static EliteIndustrialCovertCloakBonus INSTANCE = new EliteIndustrialCovertCloakBonus();

    @Override
    public int getId() {
        return  1361;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EliteIndustrialCovertCloakBonus";
    }
}
