package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The range in meters for the npcBehaviorMicroJumpAttackRange effect
 */
public class BehaviorMicroJumpAttackRange
    extends IntAttribute
{
    public static final BehaviorMicroJumpAttackRange INSTANCE = new BehaviorMicroJumpAttackRange();

    @Override
    public int getId() {
        return  2816;
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
        return "BehaviorMicroJumpAttackRange";
    }
}
