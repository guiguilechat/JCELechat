package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Increase number of available/simultaneous reaction slots
 */
public class ReactionSlotBonus
    extends IntAttribute
{
    public final static ReactionSlotBonus INSTANCE = new ReactionSlotBonus();

    @Override
    public int getId() {
        return  2661;
    }

    @Override
    public int getCatId() {
        return  7;
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
