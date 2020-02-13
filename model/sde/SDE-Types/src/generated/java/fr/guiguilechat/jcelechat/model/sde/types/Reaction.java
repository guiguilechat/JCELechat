package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;

public abstract class Reaction
    extends EveType
{
    public static final Reaction.MetaCat METACAT = new Reaction.MetaCat();

    @Override
    public IMetaCategory<Reaction> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Reaction>
    {

        @Override
        public int getCategoryId() {
            return  24;
        }

        @Override
        public String getName() {
            return "Reaction";
        }

        @Override
        public Collection<IMetaGroup<? extends Reaction>> groups() {
            return Arrays.asList();
        }
    }
}
