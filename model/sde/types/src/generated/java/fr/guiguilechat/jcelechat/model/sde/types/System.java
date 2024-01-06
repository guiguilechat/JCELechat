package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;

public abstract class System
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final System.MetaCat METACAT = new System.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<System> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<System>
    {

        @Override
        public int getCategoryId() {
            return  0;
        }

        @Override
        public String getName() {
            return "System";
        }

        @Override
        public Collection<IMetaGroup<? extends System>> groups() {
            return Arrays.asList(fr.guiguilechat.jcelechat.model.sde.types.system.System.METAGROUP);
        }
    }
}
