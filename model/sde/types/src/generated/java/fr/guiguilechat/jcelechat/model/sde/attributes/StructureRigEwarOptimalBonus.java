package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class StructureRigEwarOptimalBonus
    extends IntAttribute
{
    public static final StructureRigEwarOptimalBonus INSTANCE = new StructureRigEwarOptimalBonus();

    @Override
    public int getId() {
        return  2440;
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
        return "StructureRigEwarOptimalBonus";
    }
}
