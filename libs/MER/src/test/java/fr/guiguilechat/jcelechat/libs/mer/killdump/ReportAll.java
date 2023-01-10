package fr.guiguilechat.jcelechat.libs.mer.killdump;

public class ReportAll {

	public static void main(String[] args) {
		ReportCountByRegion.main(args);
		ReportCountIndusHS.main(args);
		ReportValueByRegion.main(args);
		ReportValueIndusHS.main(args);
		ReportNPCPct.main(args);
	}

}
