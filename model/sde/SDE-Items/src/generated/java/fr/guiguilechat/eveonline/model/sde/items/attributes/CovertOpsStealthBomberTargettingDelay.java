package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class CovertOpsStealthBomberTargettingDelay
    extends IntAttribute
{
    public final static CovertOpsStealthBomberTargettingDelay INSTANCE = new CovertOpsStealthBomberTargettingDelay();

    @Override
    public int getId() {
        return  1035;
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
        return  20000.0;
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
        return "CovertOpsStealthBomberTargettingDelay";
    }
}
