package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Moon rig bonus that increases volume of moon spew in relation to extraction time
 */
public class MoonRigSpewVolumeBonus
    extends RealAttribute
{
    public static final MoonRigSpewVolumeBonus INSTANCE = new MoonRigSpewVolumeBonus();

    @Override
    public int getId() {
        return  2710;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
