package com.example.selenium.utils;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;

public class BrowserMobProxyServiceCreator {

	private static final String NETWORK_SPEED = "networkSpeed";

	private static final Logger _logger = LogManager
			.getLogger(BrowserMobProxyServiceCreator.class);

	private static final BrowserMobProxyServiceCreator _browserMobService = new BrowserMobProxyServiceCreator();
	private static BrowserMobProxy _service;

	public static synchronized BrowserMobProxyServiceCreator getInstance() {
		return _browserMobService;
	}

	public static BrowserMobProxy getService() {
		return _service;
	}

	/**
	 * Private constructor with singleton pattern, synchronization technique
	 * guarantee multi-thread environment. Initializes BrowserMobProxy service.
	 *
	 * @author njovanovic
	 */
	private static synchronized void buildBrowserMobProxyInstance() {
		if (getService() == null) {
			setService(new BrowserMobProxyServer());
		}
	}

	private static void setService(BrowserMobProxy service) {
		BrowserMobProxyServiceCreator._service = service;
	}

	private static void startBrowserMobProxyService() {

		final String networkSpeed = System.getProperty(NETWORK_SPEED) != null
				? System.getProperty(NETWORK_SPEED)
				: StringUtils.EMPTY;

		long downSpeed = 0;
		try {
			downSpeed = Long.parseLong(networkSpeed);
		} catch (NumberFormatException e) {
			_logger.warn(
					"Invalid property value for network speed. Ignoring this option...");
		}

		if (downSpeed > 0) {
			getService().setWriteBandwidthLimit(downSpeed * 1000);

			int upSpeed = Math.round(downSpeed / 4) > 0
					? Math.round(downSpeed / 4)
					: 1;
			getService().setReadBandwidthLimit(upSpeed * 1000);

			_logger.info(String.format("Throttling network speed at %s/%s. ",
					downSpeed, upSpeed));
		}

		getService().setTrustAllServers(true);
		getService().start();

		if (_logger.isDebugEnabled())
			_logger.debug("BrowserMobProxy service started successfully!");
	}

	private BrowserMobProxyServiceCreator() {
	}

	/**
	 * This enables Har capture types.
	 *
	 * @author njovanovic
	 */
	public void enableHarCaptureTypes() {
		getService().enableHarCaptureTypes(CaptureType.REQUEST_CONTENT,
				CaptureType.RESPONSE_CONTENT, CaptureType.REQUEST_HEADERS,
				CaptureType.RESPONSE_HEADERS);
	}

	/**
	 * Gets the HAR that has been recorded.
	 *
	 * @return Har
	 * @author njovanovic
	 */
	public Har getHar() {
		return getService().getHar();
	}

	/**
	 * This sets a new HAR to be recorded.
	 *
	 * @author njovanovic
	 */
	public void newHar() {
		getService().newHar();
	}

	/**
	 * Starts BrowserMobProxy embedded server in Selenium test. It returns Proxy
	 * object that should be provided capability value in browser options
	 * settings.
	 *
	 * @return Proxy
	 * @author njovanovic
	 */
	public Proxy startBrowserMobProxy() {

		buildBrowserMobProxyInstance();
		startBrowserMobProxyService();
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(getService());

		return seleniumProxy;
	}

	public void stopService() {
		getService().stop();
		_service = null;
	}

	public boolean isStarted() {

		return getService().isStarted();
	}

	public int getPort() {
		return getService().getPort();
	}

}
