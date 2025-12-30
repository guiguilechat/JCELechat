package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ImplantSetHackingVirusCoherenceModifier
    extends IntAttribute
{
    public static final ImplantSetHackingVirusCoherenceModifier INSTANCE = new ImplantSetHackingVirusCoherenceModifier();

    @Override
    public int getId() {
        return  5799;
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
        return "ImplantSetHackingVirusCoherenceModifier";
    }
}
