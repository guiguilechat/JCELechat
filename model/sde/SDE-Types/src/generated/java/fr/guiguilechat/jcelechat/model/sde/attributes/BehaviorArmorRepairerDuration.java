package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class BehaviorArmorRepairerDuration
    extends IntAttribute
{
    public static final BehaviorArmorRepairerDuration INSTANCE = new BehaviorArmorRepairerDuration();

    @Override
    public int getId() {
        return  2633;
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
        return "BehaviorArmorRepairerDuration";
    }
}
