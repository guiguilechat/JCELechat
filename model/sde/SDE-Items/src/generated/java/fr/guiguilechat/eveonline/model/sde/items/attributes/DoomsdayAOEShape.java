package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 *  1: Fixed Cylinder (Beam)
 *  2: Cylinder moving in an arc (Slash)
 *  3: Fixed Cone
 *  4: Projected Sphere
 */
public class DoomsdayAOEShape
    extends IntAttribute
{
    public final static DoomsdayAOEShape INSTANCE = new DoomsdayAOEShape();

    @Override
    public int getId() {
        return  2429;
    }

    @Override
    public int getCatId() {
        return  39;
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
        return "DoomsdayAOEShape";
    }
}
