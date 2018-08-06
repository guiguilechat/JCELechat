package fr.guiguilechat.jcelechat.model.sde.items.types.structure;

import fr.guiguilechat.jcelechat.model.sde.items.types.Structure;

public class AdministrationHub
    extends Structure
{

    @Override
    public int getGroupId() {
        return  1409;
    }

    @Override
    public Class<?> getGroup() {
        return AdministrationHub.class;
    }
}
