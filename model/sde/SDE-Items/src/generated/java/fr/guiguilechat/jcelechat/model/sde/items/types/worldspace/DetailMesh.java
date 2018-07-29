package fr.guiguilechat.jcelechat.model.sde.items.types.worldspace;

import fr.guiguilechat.jcelechat.model.sde.items.types.WorldSpace;

public class DetailMesh
    extends WorldSpace
{

    @Override
    public int getGroupId() {
        return  1068;
    }

    @Override
    public Class<?> getGroup() {
        return DetailMesh.class;
    }
}
