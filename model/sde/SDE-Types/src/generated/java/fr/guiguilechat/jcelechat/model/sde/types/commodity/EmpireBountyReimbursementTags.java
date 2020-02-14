package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class EmpireBountyReimbursementTags
    extends Commodity
{
    public static final EmpireBountyReimbursementTags.MetaGroup METAGROUP = new EmpireBountyReimbursementTags.MetaGroup();

    @Override
    public IMetaGroup<EmpireBountyReimbursementTags> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EmpireBountyReimbursementTags>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/EmpireBountyReimbursementTags.yaml";
        private Map<String, EmpireBountyReimbursementTags> cache = (null);

        @Override
        public IMetaCategory<? super EmpireBountyReimbursementTags> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1248;
        }

        @Override
        public String getName() {
            return "EmpireBountyReimbursementTags";
        }

        @Override
        public synchronized Map<String, EmpireBountyReimbursementTags> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EmpireBountyReimbursementTags.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EmpireBountyReimbursementTags> types;
        }
    }
}
