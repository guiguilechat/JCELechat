package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The length of the jump induced by the npcBehaviorMicroJumpAttack effect in meters
 */
public class BehaviorMicroJumpAttackJumpDistance
    extends IntAttribute
{
    public static final BehaviorMicroJumpAttackJumpDistance INSTANCE = new BehaviorMicroJumpAttackJumpDistance();

    @Override
    public int getId() {
        return  2818;
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
        return "BehaviorMicroJumpAttackJumpDistance";
    }
}
