package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class AngelCartelProjectileReloadingSpeed
    extends IntAttribute
{
    public static final AngelCartelProjectileReloadingSpeed INSTANCE = new AngelCartelProjectileReloadingSpeed();

    @Override
    public int getId() {
        return  6203;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "AngelCartelProjectileReloadingSpeed";
    }
}
