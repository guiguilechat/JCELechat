package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump;

public class ReportAll {

	public static void main(String[] args) {
		ReportCountByRegion.main(args);
		ReportCountIndusHS.main(args);
		ReportNPCPct.main(args);
		ReportStructureHSCount.main(args);
		ReportStructureHSValue.main(args);
		ReportStructureLSCount.main(args);
		ReportStructureNSCount.main(args);
		ReportStructureWSCount.main(args);
		ReportValueByRegion.main(args);
		ReportValueIndusHS.main(args);
	}

}
