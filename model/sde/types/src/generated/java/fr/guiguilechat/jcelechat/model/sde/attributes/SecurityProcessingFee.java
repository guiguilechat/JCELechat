package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * ISK fee per tag to be paid when turning in a tag for a security-status gain
 */
public class SecurityProcessingFee
    extends IntAttribute
{
    public static final SecurityProcessingFee INSTANCE = new SecurityProcessingFee();

    @Override
    public int getId() {
        return  1904;
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
        return "SecurityProcessingFee";
    }
}
