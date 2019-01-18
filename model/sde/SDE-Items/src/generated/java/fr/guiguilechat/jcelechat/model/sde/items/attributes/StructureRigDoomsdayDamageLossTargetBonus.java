package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Reduction in damage lost per target on the Arcing Vorton Projector
 */
public class StructureRigDoomsdayDamageLossTargetBonus
    extends DoubleAttribute
{
    public static final StructureRigDoomsdayDamageLossTargetBonus INSTANCE = new StructureRigDoomsdayDamageLossTargetBonus();

    @Override
    public int getId() {
        return  2278;
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
        return "StructureRigDoomsdayDamageLossTargetBonus";
    }
}
