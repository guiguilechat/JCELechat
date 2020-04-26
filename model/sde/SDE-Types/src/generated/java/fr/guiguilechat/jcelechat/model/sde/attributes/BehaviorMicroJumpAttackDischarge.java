package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The capacitor discharge amount for the npcBehaviorMicroJumpAttackDischarge effect
 */
public class BehaviorMicroJumpAttackDischarge
    extends IntAttribute
{
    public static final BehaviorMicroJumpAttackDischarge INSTANCE = new BehaviorMicroJumpAttackDischarge();

    @Override
    public int getId() {
        return  2815;
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
        return "BehaviorMicroJumpAttackDischarge";
    }
}
