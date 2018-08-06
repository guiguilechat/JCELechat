package fr.guiguilechat.jcelechat.model.sde.items.types.abstrct;

import fr.guiguilechat.jcelechat.model.sde.items.types.Abstrct;

public class Audio
    extends Abstrct
{

    @Override
    public int getGroupId() {
        return  1109;
    }

    @Override
    public Class<?> getGroup() {
        return Audio.class;
    }
}
