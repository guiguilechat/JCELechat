package fr.guiguilechat.eveonline.model.esi;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESIConnection {

	private static final Logger logger = LoggerFactory.getLogger(ESIConnection.class);

	public static void main(String[] args) {
		String code = getCodeByClipboard("20322037f1d14c83a949d358277a26ce", "http://localhost/callback/");
		System.err.println("received " + code);
	}

	public static void openBrowserForApp(String appID, String calllback, String...scopes) {
		String uri = "https://login.eveonline.com/oauth/authorize/?response_type=code&redirect_uri="+calllback+"&client_id="+appID;
		if (scopes != null && scopes.length != 0) {
			StringBuilder sb = new StringBuilder(uri).append("&scopes=");
			uri = Stream.of(scopes).map(s -> " " + s).collect(() -> sb, StringBuilder::append, StringBuilder::append)
					.toString();
		}
		try {
			Desktop.getDesktop().browse(new URI(uri));
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		} catch (URISyntaxException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public static String extractStringFromClipboard() {
		StringSelection sel = new StringSelection("");
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		LinkedBlockingQueue<String> dataHolder = new LinkedBlockingQueue<>(1);
		clipboard.setContents(sel, (clip, data) -> {
			try {
				dataHolder.put((String) clipboard.getData(DataFlavor.stringFlavor));
			} catch (InterruptedException | UnsupportedFlavorException | IOException e) {
				logger.warn("while reading clipboard " + data, e);
				try {
					dataHolder.put(null);
				} catch (InterruptedException e1) {
					throw new UnsupportedOperationException("catch this", e1);
				}
			}
		});
		try {
			return dataHolder.take();
		} catch (InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public static String getCodeByClipboard(String appID, String calllback, String... scopes) {
		openBrowserForApp(appID, calllback, scopes);
		String cpData = extractStringFromClipboard();
		if (cpData == null || !cpData.startsWith(calllback + "?code=")) {
			return null;
		}
		return cpData.substring(calllback.length() + "?code=".length());
	}

}
