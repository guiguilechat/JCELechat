package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;

public class EnslavementPrograms
    extends Reaction
{

    @Override
    public int getGroupId() {
        return  882;
    }

    @Override
    public Class<?> getGroup() {
        return EnslavementPrograms.class;
    }
}
