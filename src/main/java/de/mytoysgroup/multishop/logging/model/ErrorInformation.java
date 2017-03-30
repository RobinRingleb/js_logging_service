package de.mytoysgroup.multishop.logging.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Robin Ringleb (2017)
 */

public class ErrorInformation implements Comparable<ErrorInformation> {
	private String context;
	private String deviceType;
	private String file;
	private String host;
	private int line;
	private String message;
	private String path;
	private String shopName;
	private String shopstyle;
	private Date time;
	private String browser;
	private String pageType;

	public ErrorInformation(String context, String deviceType, String file, String host, int line, String message,
			String path, String shopName, String shopstyle) {
		this.context = context;
		this.deviceType = deviceType;
		this.file = file;
		this.host = host;
		this.line = line;
		this.message = message;
		this.path = path;
		this.shopName = shopName;
		this.shopstyle = shopstyle;
	}

	public String getBrowserFromContext(String context) {
		if (context.contains("Firefox")) {
			return "Firefox";
		} else if (context.contains("Chrome")) {
			return "Chrome";
		} else if (context.contains("Trident")) {
			return "IE";
		} else {
			return "unknown Browser";
		}
	}

	public String getBrowser() {
		return browser;
	}

	public void initError() {
		this.time = new Date();
		this.browser = getBrowserFromContext(this.context);
		this.pageType = getPageTypeFromPath(this.path);
	}
	
	public String getPageTypeFromPath(String path) {
		if (path.startsWith("/c/")) {
			return "static";
		} else if (path.contains(".html") && !path.startsWith("/c/")) {
			return "pdp";
		} else if (path.startsWith("/suche/")) {
			return "suche";
		} else if (path.equals("/")) {
			return "start";
		} else {
			return "pop";
		}
	}

	public String getPageType() {
		return pageType;
	}

	public String getContext() {
		return context;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getFile() {
		return file;
	}

	public int getLine() {
		return line;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public String getShopName() {
		return shopName;
	}

	public String getHost() {
		return host;
	}

	public String getShopstyle() {
		return shopstyle;
	}

	public String getTime() {
		SimpleDateFormat f1 = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
		return f1.format(time);
	}

	@Override
	public String toString() {									// für Konsolenausgabe
		return "\n" + getTime() + " - " + getContext() + " - " + getBrowser() + " - " + getDeviceType() + " - "
				+ getFile() + " - " + getLine() + " - " + getMessage() + " - " + getHost() + " - " + getPath() + " - "
				+ getShopName() + " - " + getShopstyle();
	}

	public String printShort() {								// für HTML
		return "<br>" + getTime() + " - " + getBrowser() + " - " + getShopName() + " - " + getDeviceType() + " - "
				+ getFile() + " - " + getLine() + " - " + getMessage();
	}

	public int compareTo(ErrorInformation o) {
		String time2 = o.getTime();
		if (time2.compareTo(getTime()) > 0)
			return 1;
		if (time2.compareTo(getTime()) < 0)
			return -1;
		return 0;
	}

	public boolean equals(ErrorInformation o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (getClass() != o.getClass())
			return false;
		return compareTo((ErrorInformation) o) == 0;
	}

	public int hashCode() {
		return time.hashCode();
	}
}
