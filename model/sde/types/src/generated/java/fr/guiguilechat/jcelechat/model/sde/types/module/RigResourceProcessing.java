package fr.guiguilechat.jcelechat.model.sde.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AccessDifficultyBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Drawback;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IceHarvestCycleBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningAmountBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCost;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class RigResourceProcessing
    extends Module
{
    /**
     * Bonus to chance of opening a container.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double accessdifficultybonus;
    /**
     * used on rigs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int drawback;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double iceharvestcyclebonus;
    /**
     * Autogenerated skill attribute, miningAmountBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningamountbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * How much of the upgrade capacity is used when this is fitted to a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecost;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {UpgradeCost.INSTANCE, Drawback.INSTANCE, MiningAmountBonus.INSTANCE, Radius.INSTANCE, AccessDifficultyBonus.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, Hp.INSTANCE, MetaLevelOld.INSTANCE, RigSize.INSTANCE, IceHarvestCycleBonus.INSTANCE })));
    public static final RigResourceProcessing.MetaGroup METAGROUP = new RigResourceProcessing.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  902 :
            {
                return accessdifficultybonus;
            }
            case  1138 :
            {
                return drawback;
            }
            case  780 :
            {
                return iceharvestcyclebonus;
            }
            case  434 :
            {
                return miningamountbonus;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1153 :
            {
                return upgradecost;
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
    public IMetaGroup<RigResourceProcessing> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RigResourceProcessing>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/RigResourceProcessing.yaml";
        private Map<Integer, RigResourceProcessing> cache = (null);

        @Override
        public IMetaCategory<? super RigResourceProcessing> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1232;
        }

        @Override
        public String getName() {
            return "RigResourceProcessing";
        }

        @Override
        public synchronized Map<Integer, RigResourceProcessing> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(RigResourceProcessing.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, RigResourceProcessing> types;
        }
    }
}