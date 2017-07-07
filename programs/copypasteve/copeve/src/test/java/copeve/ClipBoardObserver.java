package copeve;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.lang.reflect.Field;

public class ClipBoardObserver {

	protected Clipboard clipboard;
	protected static Field ownerField;

	public ClipBoardObserver(Clipboard cb) {
		clipboard = cb;
		try {
			ownerField = Clipboard.class.getDeclaredField("owner");
			ownerField.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			ownerField = null;
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public ClipBoardObserver() {
		this(Toolkit.getDefaultToolkit().getSystemClipboard());
	}

	public ClipboardOwner getOwner() {
		try {
			return (ClipboardOwner) ownerField.get(clipboard);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	boolean listen = false;

	protected void register() {
		clipboard.setContents(clipboard.getContents(null), this::lostOwnerShip);
		listen = true;
	}

	public void unregister() {
		listen = false;
	}

	protected void lostOwnerShip(Clipboard cb, Transferable content) {
		if (listen) {
			onNewContent(cb.getContents(null));
			listen = false;
			try {
				Thread.sleep(20);
				register();
				Thread.sleep(20);
			} catch (InterruptedException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
			System.err.println(" owner post register is " + getOwner());
		}
	}

	public void onNewContent(Transferable content) {
		ClipboardOwner o = getOwner();
		if (o != null) {
			System.err.println(" owner is " + getOwner());
		} else {
			System.err.println(" no owner");
		}
		for (DataFlavor f : content.getTransferDataFlavors()) {
			System.err.println("accepts " + f.getHumanPresentableName() + "  - type=" + f.getMimeType());
		}
		if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			try {
				System.out.println(content.getTransferData(DataFlavor.stringFlavor));
			} catch (UnsupportedFlavorException | IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ClipBoardObserver cbo = new ClipBoardObserver();
		cbo.register();
		Thread.sleep(600000);

	}

}
