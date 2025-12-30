package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.colonyresources.ColonyReagents;

public abstract class ColonyResources
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final ColonyResources.MetaCat METACAT = new ColonyResources.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<ColonyResources> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<ColonyResources>
    {

        @Override
        public int getCategoryId() {
            return  2143;
        }

        @Override
        public String getName() {
            return "ColonyResources";
        }

        @Override
        public Collection<IMetaGroup<? extends ColonyResources>> groups() {
            return Arrays.asList(ColonyReagents.METAGROUP);
        }
    }
}
