package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Increase number of available/simultaneous reaction slots
 */
public class ReactionSlotBonus
    extends IntAttribute
{
    public static final ReactionSlotBonus INSTANCE = new ReactionSlotBonus();

    @Override
    public int getId() {
        return  2661;
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
        return "ReactionSlotBonus";
    }
}
