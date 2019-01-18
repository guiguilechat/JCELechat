package fr.guiguilechat.jcelechat.model.sde.items.types.deployable;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Deployable;
import org.yaml.snakeyaml.Yaml;

public class MobileTractorUnit
    extends Deployable
{
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ArmorUniformity;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
    /**
     * The maximum speed that the tractor beam draws objects closer at.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxTractorVelocity;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanGravimetricStrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanMagnetometricStrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanRadarStrength;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    public static final MobileTractorUnit.MetaGroup METAGROUP = new MobileTractorUnit.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  524 :
            {
                return ArmorUniformity;
            }
            case  73 :
            {
                return Duration;
            }
            case  192 :
            {
                return MaxLockedTargets;
            }
            case  54 :
            {
                return MaxRange;
            }
            case  76 :
            {
                return MaxTargetRange;
            }
            case  1045 :
            {
                return MaxTractorVelocity;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  211 :
            {
                return ScanGravimetricStrength;
            }
            case  210 :
            {
                return ScanMagnetometricStrength;
            }
            case  208 :
            {
                return ScanRadarStrength;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<MobileTractorUnit> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MobileTractorUnit>
    {
        public static final String RESOURCE_PATH = "SDE/items/deployable/MobileTractorUnit.yaml";
        private Map<String, MobileTractorUnit> cache = (null);

        @Override
        public IMetaCategory<? super MobileTractorUnit> category() {
            return Deployable.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1250;
        }

        @Override
        public String getName() {
            return "MobileTractorUnit";
        }

        @Override
        public synchronized Map<String, MobileTractorUnit> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MobileTractorUnit.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MobileTractorUnit> items;
        }
    }
}
