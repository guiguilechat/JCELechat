package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.station.StationServices;

public abstract class Station
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Station.MetaCat METACAT = new Station.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Station> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Station>
    {

        @Override
        public int getCategoryId() {
            return  3;
        }

        @Override
        public String getName() {
            return "Station";
        }

        @Override
        public Collection<IMetaGroup<? extends Station>> groups() {
            return Arrays.asList(fr.guiguilechat.jcelechat.model.sde.types.station.Station.METAGROUP, StationServices.METAGROUP);
        }
    }
}
