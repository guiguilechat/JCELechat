package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorArmorRepairerDischarge
    extends IntAttribute
{
    public static final BehaviorArmorRepairerDischarge INSTANCE = new BehaviorArmorRepairerDischarge();

    @Override
    public int getId() {
        return  2634;
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
        return "BehaviorArmorRepairerDischarge";
    }
}
