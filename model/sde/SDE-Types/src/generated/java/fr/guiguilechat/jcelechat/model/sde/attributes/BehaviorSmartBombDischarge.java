package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The capacitor discharge for the npcBehaviorSmartBomb effect
 */
public class BehaviorSmartBombDischarge
    extends IntAttribute
{
    public static final BehaviorSmartBombDischarge INSTANCE = new BehaviorSmartBombDischarge();

    @Override
    public int getId() {
        return  2814;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "BehaviorSmartBombDischarge";
    }
}
