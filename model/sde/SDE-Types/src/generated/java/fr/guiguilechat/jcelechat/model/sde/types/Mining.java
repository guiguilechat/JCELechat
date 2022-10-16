package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;

public abstract class Mining
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Mining.MetaCat METACAT = new Mining.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Mining> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Mining>
    {

        @Override
        public int getCategoryId() {
            return  2107;
        }

        @Override
        public String getName() {
            return "Mining";
        }

        @Override
        public Collection<IMetaGroup<? extends Mining>> groups() {
            return Arrays.asList();
        }
    }
}
