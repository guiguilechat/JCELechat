package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The Preferred signature radius for attack. If it is 0 or below it uses the ships signature radius instead.
 */
public class AIPreferredSignatureRadius
    extends IntAttribute
{
    public static final AIPreferredSignatureRadius INSTANCE = new AIPreferredSignatureRadius();

    @Override
    public int getId() {
        return  1655;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return -1.0;
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
        return "AIPreferredSignatureRadius";
    }
}
