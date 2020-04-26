package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 *  1: Fixed Cylinder (Beam)
 *  2: Cylinder moving in an arc (Slash)
 *  3: Fixed Cone
 *  4: Projected Sphere
 */
public class DoomsdayAOEShape
    extends IntAttribute
{
    public static final DoomsdayAOEShape INSTANCE = new DoomsdayAOEShape();

    @Override
    public int getId() {
        return  2429;
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
