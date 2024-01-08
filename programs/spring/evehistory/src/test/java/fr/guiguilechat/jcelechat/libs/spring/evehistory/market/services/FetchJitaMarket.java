package fr.guiguilechat.jcelechat.libs.spring.evehistory.market.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchResult;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.MarketFetchResult.STATUS;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.market.model.ObservedRegion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FetchJitaMarket {

	public static record Fetch(MarketFetchResult result, List<MarketFetchLine> lines) {
	}

	public static record SavedLines(List<Fetch> fetched) {
		public SavedLines() {
			this(new ArrayList<>());
		} 
	}

	static final String FILE_DATA = "Jita4.json";
	static final String FILE_compressed = "src/test/resources/marketdump/Jita4.zip";

	public static void main(String[] args)
			throws InterruptedException, StreamWriteException, DatabindException, IOException {
		MarketFetchService service = new MarketFetchService();
		String lastEtag = null;
		int JitaId = 10000002;
		ObservedRegion theForge = ObservedRegion.builder().regionId(JitaId).active(true).build();

		SavedLines lines = new SavedLines();
		while (lines.fetched().size() < 3) {
			if (lastEtag != null) {
				log.debug("done " + lines.fetched().size() + ", waiting");
				Thread.sleep(60000);
			}
			log.debug("fetching result with etag=" + lastEtag);
			Entry<MarketFetchResult, List<MarketFetchLine>> e = service.fetchMarketNoDB(theForge, lastEtag);
			MarketFetchResult fetchedResult = e.getKey();
			List<MarketFetchLine> fetchedLines = e.getValue();
			fetchedLines.forEach(l -> l.setFetchResult(null));
			if (fetchedResult.getStatus() == STATUS.CACHED || fetchedResult.getStatus() == STATUS.FAIL) {
				log.info(" invalid or cached result " + fetchedResult.getStatus() + " : " + fetchedResult.getErrors());
			} else {
				log.info(" valid result with etag " + fetchedResult.getEtag());
				lines.fetched.add(new Fetch(fetchedResult, fetchedLines));
				lastEtag = fetchedResult.getEtag();
			}
		}
		File outFile = new File(FILE_compressed);
		outFile.getParentFile().mkdirs();
		outFile.delete();
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(outFile));
		ZipEntry zipEntry = new ZipEntry(FILE_DATA);
		zipOut.putNextEntry(zipEntry);

		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.writerWithDefaultPrettyPrinter().writeValue(zipOut, lines);
		log.info("file written : " + outFile.getAbsolutePath());
	}

	public static SavedLines loadTestLines() throws IOException {
		ZipInputStream zis = new ZipInputStream(new FileInputStream(FILE_compressed));
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		zis.getNextEntry();
		return mapper.readerFor(SavedLines.class).readValue(zis);
	}

}
