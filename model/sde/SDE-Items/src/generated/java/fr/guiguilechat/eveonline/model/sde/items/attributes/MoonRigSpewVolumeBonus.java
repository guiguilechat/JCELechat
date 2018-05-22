package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Moon rig bonus that increases volume of moon spew in relation to extraction time
 */
public class MoonRigSpewVolumeBonus
    extends DoubleAttribute
{
    public final static MoonRigSpewVolumeBonus INSTANCE = new MoonRigSpewVolumeBonus();

    @Override
    public int getId() {
        return  2710;
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
        return "MoonRigSpewVolumeBonus";
    }
}
