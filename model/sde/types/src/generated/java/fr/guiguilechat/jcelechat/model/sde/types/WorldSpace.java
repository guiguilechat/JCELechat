package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;

public abstract class WorldSpace
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final WorldSpace.MetaCat METACAT = new WorldSpace.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<WorldSpace> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<WorldSpace>
    {

        @Override
        public int getCategoryId() {
            return  26;
        }

        @Override
        public String getName() {
            return "WorldSpace";
        }

        @Override
        public Collection<IMetaGroup<? extends WorldSpace>> groups() {
            return Arrays.asList(fr.guiguilechat.jcelechat.model.sde.types.worldspace.WorldSpace.METAGROUP);
        }
    }
}
