package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Autogenerated skill attribute, diplomacyMutator
 */
public class DiplomacyMutator
    extends RealAttribute
{
    public static final DiplomacyMutator INSTANCE = new DiplomacyMutator();

    @Override
    public int getId() {
        return  414;
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
        return "DiplomacyMutator";
    }
}
