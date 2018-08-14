package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class EmpireBountyReimbursementTags
    extends Commodity
{
    public final static EmpireBountyReimbursementTags.MetaGroup METAGROUP = new EmpireBountyReimbursementTags.MetaGroup();

    @Override
    public IMetaGroup<EmpireBountyReimbursementTags> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EmpireBountyReimbursementTags>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/EmpireBountyReimbursementTags.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(EmpireBountyReimbursementTags.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EmpireBountyReimbursementTags> items;
        }
    }
}
