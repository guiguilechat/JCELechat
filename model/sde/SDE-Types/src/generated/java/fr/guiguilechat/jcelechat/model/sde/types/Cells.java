package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.cells.PhysicalPortals;

public abstract class Cells
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Cells.MetaCat METACAT = new Cells.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Cells> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Cells>
    {

        @Override
        public int getCategoryId() {
            return  59;
        }

        @Override
        public String getName() {
            return "Cells";
        }

        @Override
        public Collection<IMetaGroup<? extends Cells>> groups() {
            return Arrays.asList(PhysicalPortals.METAGROUP);
        }
    }
}
