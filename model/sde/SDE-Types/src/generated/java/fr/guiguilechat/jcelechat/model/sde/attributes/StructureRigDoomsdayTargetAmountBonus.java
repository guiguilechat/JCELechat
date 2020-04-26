package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to Arcving Vorton Projector
 */
public class StructureRigDoomsdayTargetAmountBonus
    extends IntAttribute
{
    public static final StructureRigDoomsdayTargetAmountBonus INSTANCE = new StructureRigDoomsdayTargetAmountBonus();

    @Override
    public int getId() {
        return  2277;
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
        return "StructureRigDoomsdayTargetAmountBonus";
    }
}
