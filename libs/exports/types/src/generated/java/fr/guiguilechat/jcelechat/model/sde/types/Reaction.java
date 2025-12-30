package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;

public abstract class Reaction
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Reaction.MetaCat METACAT = new Reaction.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

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
