package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.AbyssalFilaments;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.AccelerationGateKeys;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.AdvancedCapitalConstructionComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.ArtifactsAndPrototypes;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Biohazard;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.CapitalConstructionComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Commodities;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.ConstructionComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.CriminalTags;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.DataInterfaces;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Datacores;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.DecryptorsSleepers;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.DecryptorsTakmahl;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.DecryptorsTalocan;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.DecryptorsYanJung;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Drugs;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.EmpireBountyReimbursementTags;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.EmpireInsigniaDrops;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Frozen;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.General;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.HybridTechComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Identification;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Lease;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Livestock;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.MaterialsAndCompounds;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Miscellaneous;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Mutaplasmids;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.OverseerPersonalEffects;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Radioactive;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Refinables;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.ResearchData;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.SecurityTags;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.ShipLogs;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.SlaveReception;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.SleeperComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.StrongBoxes;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.StructureComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.TechnicalDataChips;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.Tool;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.TriglavianData;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.TriglavianDatastreams;
import fr.guiguilechat.jcelechat.model.sde.items.types.commodity.UnknownComponents;

public abstract class Commodity
    extends Item
{
    public final static Commodity.MetaCat METACAT = new Commodity.MetaCat();

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
            return Arrays.asList(General.METAGROUP, Frozen.METAGROUP, Radioactive.METAGROUP, Livestock.METAGROUP, Biohazard.METAGROUP, Drugs.METAGROUP, Miscellaneous.METAGROUP, Tool.METAGROUP, Datacores.METAGROUP, ConstructionComponents.METAGROUP, Refinables.METAGROUP, ShipLogs.METAGROUP, CriminalTags.METAGROUP, EmpireInsigniaDrops.METAGROUP, AccelerationGateKeys.METAGROUP, OverseerPersonalEffects.METAGROUP, Identification.METAGROUP, Commodities.METAGROUP, ArtifactsAndPrototypes.METAGROUP, MaterialsAndCompounds.METAGROUP, StructureComponents.METAGROUP, Lease.METAGROUP, DataInterfaces.METAGROUP, DecryptorsSleepers.METAGROUP, DecryptorsYanJung.METAGROUP, DecryptorsTakmahl.METAGROUP, DecryptorsTalocan.METAGROUP, CapitalConstructionComponents.METAGROUP, SlaveReception.METAGROUP, SleeperComponents.METAGROUP, AdvancedCapitalConstructionComponents.METAGROUP, HybridTechComponents.METAGROUP, ResearchData.METAGROUP, SecurityTags.METAGROUP, EmpireBountyReimbursementTags.METAGROUP, UnknownComponents.METAGROUP, StrongBoxes.METAGROUP, TechnicalDataChips.METAGROUP, Mutaplasmids.METAGROUP, AbyssalFilaments.METAGROUP, TriglavianData.METAGROUP, TriglavianDatastreams.METAGROUP);
        }
    }
}
