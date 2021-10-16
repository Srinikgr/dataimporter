package org.sample.xmlreader.core.services;
import org.sample.xmlreader.core.models.ChannelList;
public interface XMLReaderService {
ChannelList readXMLFromURL(String responseURL);
}
