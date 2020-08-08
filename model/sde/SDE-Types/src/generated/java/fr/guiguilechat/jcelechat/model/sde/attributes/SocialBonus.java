package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus To standing gain towards non CONCORD npcs  
 */
public class SocialBonus
    extends IntAttribute
{
    public static final SocialBonus INSTANCE = new SocialBonus();

    @Override
    public int getId() {
        return  362;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  100.0;
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
        return "SocialBonus";
    }
}
