package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The duration of the npcBehaviorMicroJumpAttack effect from the time the effect is activated on a ship, until the ship jumps
 */
public class BehaviorMicroJumpAttackDuration
    extends IntAttribute
{
    public static final BehaviorMicroJumpAttackDuration INSTANCE = new BehaviorMicroJumpAttackDuration();

    @Override
    public int getId() {
        return  2819;
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
        return "BehaviorMicroJumpAttackDuration";
    }
}
