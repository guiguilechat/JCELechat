package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class SalvageContainers
    extends Infantry
{

    @Override
    public int getGroupId() {
        return  367774;
    }

    @Override
    public Class<?> getGroup() {
        return SalvageContainers.class;
    }
}
