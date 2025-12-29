package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.expertsystems.PromotionalExpertSystems;
import fr.guiguilechat.jcelechat.model.sde.types.expertsystems.StandardExpertSystems;

public abstract class ExpertSystems
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final ExpertSystems.MetaCat METACAT = new ExpertSystems.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<ExpertSystems> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<ExpertSystems>
    {

        @Override
        public int getCategoryId() {
            return  2100;
        }

        @Override
        public String getName() {
            return "ExpertSystems";
        }

        @Override
        public Collection<IMetaGroup<? extends ExpertSystems>> groups() {
            return Arrays.asList(PromotionalExpertSystems.METAGROUP, StandardExpertSystems.METAGROUP);
        }
    }
}
