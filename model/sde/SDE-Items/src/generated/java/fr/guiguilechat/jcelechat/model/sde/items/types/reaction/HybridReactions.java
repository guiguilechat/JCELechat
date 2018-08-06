package fr.guiguilechat.jcelechat.model.sde.items.types.reaction;

import fr.guiguilechat.jcelechat.model.sde.items.types.Reaction;

public class HybridReactions
    extends Reaction
{

    @Override
    public int getGroupId() {
        return  977;
    }

    @Override
    public Class<?> getGroup() {
        return HybridReactions.class;
    }
}
