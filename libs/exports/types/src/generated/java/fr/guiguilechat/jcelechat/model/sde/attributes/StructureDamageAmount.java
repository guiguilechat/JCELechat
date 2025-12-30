package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * An amount to modify the structural damage by.
 */
public class StructureDamageAmount
    extends IntAttribute
{
    public static final StructureDamageAmount INSTANCE = new StructureDamageAmount();

    @Override
    public int getId() {
        return  83;
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
        return "StructureDamageAmount";
    }
}
