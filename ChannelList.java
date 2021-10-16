package org.sample.xmlreader.core.models;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType; 
import javax.xml.bind.annotation.XmlAccessorType; 
import javax.xml.bind.annotation.XmlElement;
 import javax.xml.bind.annotation.XmlRootElement;
/**
* @author Srinivasan
*
*/
@XmlRootElement(name = "Channels") 
@XmlAccessorType(XmlAccessType.FIELD)
public class ChannelList implements Serializable {
// Generated serialVersionUID
private static final long serialVersionUID = -7423090858185520203L;
// Instance of one product @XmlElement(name = "Item") 
private List<Item> item;
public ChannelList() { super();
}
/**
* @param item
*/
public ChannelList(List<Item> item) { 
super();
this.item = item;
}
/**
* @return the item
*/
public List<Item> getItem() {
 return item;
}
/**
*@param item
*the product to set
*/
public void setItem(List<Item> item) { 
this.item = item;
}
/**
*Overridden toString() method
*/ 
@Override
public String toString() {
return "ChannelList [item=" + item + "]";
}
}
