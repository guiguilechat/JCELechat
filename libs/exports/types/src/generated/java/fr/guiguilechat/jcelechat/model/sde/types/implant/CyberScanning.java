package fr.guiguilechat.jcelechat.model.sde.types.implant;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AccessDifficultyBonusModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Implantness;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxScanDeviationModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusCoherenceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Implant;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CyberScanning
    extends Implant
{
    /**
     * Bonus to chance of opening a container.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double accessdifficultybonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int accessdifficultybonusmodifier;
    /**
     * Bonus to duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationbonus;
    /**
     * Whether an item is an implant or not
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int implantness;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxscandeviationmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanstrengthbonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * Adds to the virus coherence of profession modules
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int viruscoherencebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int virusstrengthbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DurationBonus.INSTANCE, MaxScanDeviationModifier.INSTANCE, RequiredSkill1Level.INSTANCE, TechLevel.INSTANCE, AccessDifficultyBonus.INSTANCE, RequiredSkill1 .INSTANCE, AccessDifficultyBonusModifier.INSTANCE, Implantness.INSTANCE, VirusCoherenceBonus.INSTANCE, ScanStrengthBonus.INSTANCE, VirusStrengthBonus.INSTANCE })));
    public static final CyberScanning.MetaGroup METAGROUP = new CyberScanning.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  902 :
            {
                return accessdifficultybonus;
            }
            case  1160 :
            {
                return accessdifficultybonusmodifier;
            }
            case  66 :
            {
                return durationbonus;
            }
            case  331 :
            {
                return implantness;
            }
            case  1156 :
            {
                return maxscandeviationmodifier;
            }
            case  846 :
            {
                return scanstrengthbonus;
            }
            case  422 :
            {
                return techlevel;
            }
            case  1915 :
            {
                return viruscoherencebonus;
            }
            case  1918 :
            {
                return virusstrengthbonus;
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
    public IMetaGroup<CyberScanning> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CyberScanning>
    {
        public static final String RESOURCE_PATH = "SDE/types/implant/CyberScanning.yaml";
        private Map<Integer, CyberScanning> cache = (null);

        @Override
        public IMetaCategory<? super CyberScanning> category() {
            return Implant.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1230;
        }

        @Override
        public String getName() {
            return "CyberScanning";
        }

        @Override
        public synchronized Map<Integer, CyberScanning> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CyberScanning.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CyberScanning> types;
        }
    }
}
