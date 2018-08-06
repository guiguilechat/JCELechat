package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class NonInteractableObject
    extends Celestial
{

    @Override
    public int getGroupId() {
        return  1975;
    }

    @Override
    public Class<?> getGroup() {
        return NonInteractableObject.class;
    }
}
