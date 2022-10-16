package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorMicroWarpDriveSignatureRadiusBonus
    extends IntAttribute
{
    public static final BehaviorMicroWarpDriveSignatureRadiusBonus INSTANCE = new BehaviorMicroWarpDriveSignatureRadiusBonus();

    @Override
    public int getId() {
        return  2617;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "BehaviorMicroWarpDriveSignatureRadiusBonus";
    }
}
