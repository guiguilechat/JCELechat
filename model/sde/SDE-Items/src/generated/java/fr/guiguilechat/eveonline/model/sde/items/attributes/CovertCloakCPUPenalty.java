package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class CovertCloakCPUPenalty
    extends IntAttribute
{
    public final static CovertCloakCPUPenalty INSTANCE = new CovertCloakCPUPenalty();

    @Override
    public int getId() {
        return  1871;
    }

    @Override
    public int getCatId() {
        return  0;
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
        return "CovertCloakCPUPenalty";
    }
}
