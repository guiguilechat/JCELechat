package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.FilamentDescriptionMessageID;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class ExpiredAbyssalBattlefieldsFilaments
    extends Commodity
{
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(561098)
    public int filamentdescriptionmessageid;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FilamentDescriptionMessageID.INSTANCE })));
    public static final ExpiredAbyssalBattlefieldsFilaments.MetaGroup METAGROUP = new ExpiredAbyssalBattlefieldsFilaments.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  3026 :
            {
                return filamentdescriptionmessageid;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<ExpiredAbyssalBattlefieldsFilaments> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ExpiredAbyssalBattlefieldsFilaments>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/ExpiredAbyssalBattlefieldsFilaments.yaml";
        private Map<Integer, ExpiredAbyssalBattlefieldsFilaments> cache = (null);

        @Override
        public IMetaCategory<? super ExpiredAbyssalBattlefieldsFilaments> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4776;
        }

        @Override
        public String getName() {
            return "ExpiredAbyssalBattlefieldsFilaments";
        }

        @Override
        public synchronized Map<Integer, ExpiredAbyssalBattlefieldsFilaments> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ExpiredAbyssalBattlefieldsFilaments.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, ExpiredAbyssalBattlefieldsFilaments> types;
        }
    }
}
