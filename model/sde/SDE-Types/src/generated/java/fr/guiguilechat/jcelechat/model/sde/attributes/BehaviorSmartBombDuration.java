package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration of npcBehaviorSmartBombDuration effect
 */
public class BehaviorSmartBombDuration
    extends IntAttribute
{
    public static final BehaviorSmartBombDuration INSTANCE = new BehaviorSmartBombDuration();

    @Override
    public int getId() {
        return  2812;
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
        return "BehaviorSmartBombDuration";
    }
}
