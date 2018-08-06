package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;

public class SimpleBiochemicalReactions
    extends Reaction
{

    @Override
    public int getGroupId() {
        return  661;
    }

    @Override
    public Class<?> getGroup() {
        return SimpleBiochemicalReactions.class;
    }
}
