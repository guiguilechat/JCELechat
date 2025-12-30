package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This doesn't actually do anything... It's just to show the omega set bonus % in the client without also showing the real dogma value at 1.0x on the implants.
 */
public class ImplantSetHackingVirusCoherenceOmegaSetBonusFAKE
    extends IntAttribute
{
    public static final ImplantSetHackingVirusCoherenceOmegaSetBonusFAKE INSTANCE = new ImplantSetHackingVirusCoherenceOmegaSetBonusFAKE();

    @Override
    public int getId() {
        return  5800;
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
        return "ImplantSetHackingVirusCoherenceOmegaSetBonusFAKE";
    }
}
