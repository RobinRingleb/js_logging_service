package de.mytoysgroup.multishop.logging.model;

import java.util.Iterator;
import java.util.List;

/**
 * @author Robin Ringleb (2017)
 */

public class ErrorCount {
	private List<ErrorInformation> errorList;
	private int countBrowser;
	private int countPageTypeForBrowser;

	public ErrorCount(List<ErrorInformation> errorList) {
		this.errorList = errorList;
	}

	public int getCountBrowser(String browser) {
		countBrowser = 0;
		for (Iterator<ErrorInformation> i = errorList.iterator(); i.hasNext();) {
			String browserWork = i.next().getBrowser();
			if (browserWork.contains(browser) || browserWork.equalsIgnoreCase(browser)) {
				countBrowser++;
			}
		}
		return countBrowser;
	}

	public int getCountPageTypeForBrowser(String page, String browser) {
		countPageTypeForBrowser = 0;
		for (Iterator<ErrorInformation> i = errorList.iterator(); i.hasNext();) {
			ErrorInformation temp = i.next();
			String pageWork = temp.getPageType();
			String browserWork = temp.getBrowser();
			if ((pageWork.contains(page) || pageWork.equalsIgnoreCase(page))
					&& (browserWork.contains(browser) || browserWork.equalsIgnoreCase(browser))) {
				countPageTypeForBrowser++;
			}
		}
		return countPageTypeForBrowser;
	}
}
