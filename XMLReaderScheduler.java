package org.sample.xmlreader.core.schedulers;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler; 
import org.osgi.service.component.annotations.Activate;
 import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
 import org.osgi.service.component.annotations.Modified; 
import org.osgi.service.component.annotations.Reference; 
import org.osgi.service.metatype.annotations.Designate;
import org.sample.xmlreader.core.configurations.XMLReaderConfiguration; 
import org.sample.xmlreader.core.models.ChannelList;
import org.sample.xmlreader.core.services.XMLReaderService; 
import org.sample.xmlreader.core.services.XMLWriterService;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* @author Srinivasan
**/
@Component(immediate = true, service = Runnable.class) 
@Designate(ocd = XMLReaderConfiguration.class)
public class XMLReaderScheduler implements Runnable {
 // Logger
private final Logger log = LoggerFactory.getLogger(this.getClass());
// Id of the scheduler based on its name 
private int schedulerId;
// Scheduler instance injected 
@Reference
private Scheduler scheduler;
// XMLReaderService is injected 
@Reference
private XMLReaderService xmlReaderService;
// XMLWriterService is injected
@Reference
private XMLWriterService xmlWriterService;
// URL of the XML response 
private String url;
// Flag to check if the scheduler is enabled
 private boolean isEnabled;
// Path where data is to be stored in JCR 
private String jcrPath;
/**
*Activate method to initialize stuff
*
*@param xmlReaderConfiguration
*/ 
@Activate
protected void activate(XMLReaderConfiguration xmlReaderConfiguration) {
schedulerId = xmlReaderConfiguration.name().hashCode();
url = xmlReaderConfiguration.xmlResponseURL();
 isEnabled = xmlReaderConfiguration.enabled();
 jcrPath = xmlReaderConfiguration.jcrPath();
}
/**
*Modifies the scheduler id on modification
*
*@param xmlReaderConfiguration
 */ 
@Modified
protected void modified(XMLReaderConfiguration xmlReaderConfiguration) {
// Removing scheduler 
removeScheduler();
// Updating the scheduler id
schedulerId = xmlReaderConfiguration.name().hashCode();
// Again adding the scheduler 
addScheduler(xmlReaderConfiguration);
}
/**
*This method deactivates the scheduler and removes it
*
*@param xmlReaderConfiguration
*/ 
@Deactivate
protected void deactivate(XMLReaderConfiguration xmlReaderConfiguration) {
// Removing the scheduler removeScheduler();
}
 /**
*This method removes the scheduler
*/
private void removeScheduler() { 
log.info("Removing scheduler: {}", schedulerId);
// Unscheduling/removing the scheduler 
scheduler.unschedule(String.valueOf(schedulerId));
}
/**
*This method adds the scheduler
*
*@param xmlReaderConfiguration
*/
private void addScheduler(XMLReaderConfiguration xmlReaderConfiguration) {
// Check if the scheduler is enabled
 if (isEnabled) {
// Scheduler option takes the cron expression as a parameter and run accordingly
ScheduleOptions scheduleOptions = scheduler.EXPR(xmlReaderConfiguration.cronExpression());
// Adding some parameters 
scheduleOptions.name(xmlReaderConfiguration.name());
scheduleOptions.canRunConcurrently(false);
// Scheduling the job
 scheduler.schedule(this, scheduleOptions);
 log.info("Scheduler added");
} else {
log.info("Scheduler is disabled");
}
}
/**
*Overridden run method to execute Job
*/ @Override
public void run() { 
if (isEnabled) {
ChannelList channelList = null;
// Reads XML data from a response
 if (url != null && !url.isEmpty()) {
channelList = xmlReaderService.readXMLFromURL(url); xmlWriterService.writeXMLToJCR(channelList, jcrPath, "url");
 }
}
}
}
