package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class NpcStructureStasisWebificationBonus
    extends DoubleAttribute
{
    public final static NpcStructureStasisWebificationBonus INSTANCE = new NpcStructureStasisWebificationBonus();

    @Override
    public int getId() {
        return  2735;
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
        return "NpcStructureStasisWebificationBonus";
    }
}
