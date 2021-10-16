package org.sample.xmlreader.core.services;
import org.sample.xmlreader.core.models.ChannelList;
public interface XMLWriterService {
void writeXMLToJCR(ChannelList productList, String jcrPath, String from);
}
