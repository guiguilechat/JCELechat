package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus attribute to entity Target Switch Delay
 */
public class ControlTowerEwTargetSwitchDelayBonus
    extends IntAttribute
{
    public static final ControlTowerEwTargetSwitchDelayBonus INSTANCE = new ControlTowerEwTargetSwitchDelayBonus();

    @Override
    public int getId() {
        return  770;
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
        return "ControlTowerEwTargetSwitchDelayBonus";
    }
}
