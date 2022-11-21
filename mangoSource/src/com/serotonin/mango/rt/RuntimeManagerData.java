package com.serotonin.mango.rt;

import java.util.List;
import java.util.Map;

import com.serotonin.mango.rt.dataImage.DataPointListener;
import com.serotonin.mango.rt.dataImage.DataPointRT;
import com.serotonin.mango.rt.dataSource.DataSourceRT;
import com.serotonin.mango.rt.event.SimpleEventDetector;
import com.serotonin.mango.rt.event.compound.CompoundEventDetectorRT;
import com.serotonin.mango.rt.event.maintenance.MaintenanceEventRT;
import com.serotonin.mango.rt.link.PointLinkRT;
import com.serotonin.mango.rt.publish.PublisherRT;

public class RuntimeManagerData {
	public List<DataSourceRT> runningDataSources;
	/**
	 * Provides a quick lookup map of the running data points.
	 */
	public Map<Integer, DataPointRT> dataPoints;
	/**
	 * The list of point listeners, kept here such that listeners can be notified of point initializations (i.e. a
	 * listener can register itself before the point is enabled).
	 */
	public Map<Integer, DataPointListener> dataPointListeners;
	/**
	 * Store of enabled event detectors.
	 */
	public Map<String, SimpleEventDetector> simpleEventDetectors;
	/**
	 * Store of enabled compound event detectors.
	 */
	public Map<Integer, CompoundEventDetectorRT> compoundEventDetectors;
	/**
	 * Store of enabled publishers
	 */
	public List<PublisherRT<?>> runningPublishers;
	/**
	 * Store of enabled point links
	 */
	public List<PointLinkRT> pointLinks;
	/**
	 * Store of maintenance events
	 */
	public List<MaintenanceEventRT> maintenanceEvents;
	public boolean started;

	public RuntimeManagerData(List<DataSourceRT> runningDataSources, Map<Integer, DataPointRT> dataPoints,
			Map<Integer, DataPointListener> dataPointListeners, Map<String, SimpleEventDetector> simpleEventDetectors,
			Map<Integer, CompoundEventDetectorRT> compoundEventDetectors, List<PublisherRT<?>> runningPublishers,
			List<PointLinkRT> pointLinks, List<MaintenanceEventRT> maintenanceEvents, boolean started) {
		this.runningDataSources = runningDataSources;
		this.dataPoints = dataPoints;
		this.dataPointListeners = dataPointListeners;
		this.simpleEventDetectors = simpleEventDetectors;
		this.compoundEventDetectors = compoundEventDetectors;
		this.runningPublishers = runningPublishers;
		this.pointLinks = pointLinks;
		this.maintenanceEvents = maintenanceEvents;
		this.started = started;
	}
}