package fr.guiguilechat.jcelechat.model.sde.items.types.structure;

import fr.guiguilechat.jcelechat.model.sde.items.types.Structure;

public class Laboratory
    extends Structure
{

    @Override
    public int getGroupId() {
        return  1405;
    }

    @Override
    public Class<?> getGroup() {
        return Laboratory.class;
    }
}
