package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.AbyssalFilaments;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.AccelerationGateKeys;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.AdvancedCapitalConstructionComponents;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.ArtifactsAndPrototypes;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Biohazard;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.CapitalConstructionComponents;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Commodities;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.ConstructionComponents;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.CriminalTags;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.DataInterfaces;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Datacores;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.DecryptorsSleepers;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.DecryptorsTakmahl;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.DecryptorsTalocan;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.DecryptorsYanJung;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Drugs;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.EmpireBountyReimbursementTags;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.EmpireInsigniaDrops;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Frozen;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.General;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.HybridTechComponents;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Identification;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.JumpFilaments;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Lease;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Livestock;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.MaterialsAndCompounds;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Miscellaneous;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Mutaplasmids;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.OverseerPersonalEffects;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Radioactive;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Refinables;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.ResearchData;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.SecurityTags;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.ShipLogs;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.SlaveReception;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.SleeperComponents;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.StrongBoxes;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.StructureComponents;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.TechnicalDataChips;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Tool;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.TriglavianData;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.TriglavianDatastreams;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.UnknownComponents;

public abstract class Commodity
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Commodity.MetaCat METACAT = new Commodity.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Commodity> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Commodity>
    {

        @Override
        public int getCategoryId() {
            return  17;
        }

        @Override
        public String getName() {
            return "Commodity";
        }

        @Override
        public Collection<IMetaGroup<? extends Commodity>> groups() {
            return Arrays.asList(General.METAGROUP, Frozen.METAGROUP, Radioactive.METAGROUP, Livestock.METAGROUP, Biohazard.METAGROUP, Drugs.METAGROUP, Miscellaneous.METAGROUP, Tool.METAGROUP, Datacores.METAGROUP, ConstructionComponents.METAGROUP, Refinables.METAGROUP, ShipLogs.METAGROUP, CriminalTags.METAGROUP, EmpireInsigniaDrops.METAGROUP, AccelerationGateKeys.METAGROUP, OverseerPersonalEffects.METAGROUP, Identification.METAGROUP, Commodities.METAGROUP, ArtifactsAndPrototypes.METAGROUP, MaterialsAndCompounds.METAGROUP, StructureComponents.METAGROUP, Lease.METAGROUP, DataInterfaces.METAGROUP, DecryptorsSleepers.METAGROUP, DecryptorsYanJung.METAGROUP, DecryptorsTakmahl.METAGROUP, DecryptorsTalocan.METAGROUP, CapitalConstructionComponents.METAGROUP, SlaveReception.METAGROUP, SleeperComponents.METAGROUP, AdvancedCapitalConstructionComponents.METAGROUP, HybridTechComponents.METAGROUP, ResearchData.METAGROUP, SecurityTags.METAGROUP, EmpireBountyReimbursementTags.METAGROUP, UnknownComponents.METAGROUP, StrongBoxes.METAGROUP, TechnicalDataChips.METAGROUP, Mutaplasmids.METAGROUP, AbyssalFilaments.METAGROUP, JumpFilaments.METAGROUP, TriglavianData.METAGROUP, TriglavianDatastreams.METAGROUP);
        }
    }
}
