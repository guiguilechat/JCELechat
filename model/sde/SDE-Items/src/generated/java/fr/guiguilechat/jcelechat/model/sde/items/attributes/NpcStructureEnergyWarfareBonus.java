package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class NpcStructureEnergyWarfareBonus
    extends DoubleAttribute
{
    public final static NpcStructureEnergyWarfareBonus INSTANCE = new NpcStructureEnergyWarfareBonus();

    @Override
    public int getId() {
        return  2736;
    }

    @Override
    public int getCatId() {
        return  42;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "NpcStructureEnergyWarfareBonus";
    }
}
