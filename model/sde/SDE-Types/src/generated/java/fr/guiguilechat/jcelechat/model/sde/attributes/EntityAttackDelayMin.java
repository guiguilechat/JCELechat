package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Minimum attack delay time for entity.
 */
public class EntityAttackDelayMin
    extends IntAttribute
{
    public static final EntityAttackDelayMin INSTANCE = new EntityAttackDelayMin();

    @Override
    public int getId() {
        return  475;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EntityAttackDelayMin";
    }
}
