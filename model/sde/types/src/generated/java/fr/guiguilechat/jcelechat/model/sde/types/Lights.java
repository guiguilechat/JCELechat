package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.lights.PointLights;

public abstract class Lights
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Lights.MetaCat METACAT = new Lights.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Lights> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Lights>
    {

        @Override
        public int getCategoryId() {
            return  54;
        }

        @Override
        public String getName() {
            return "Lights";
        }

        @Override
        public Collection<IMetaGroup<? extends Lights>> groups() {
            return Arrays.asList(PointLights.METAGROUP);
        }
    }
}
