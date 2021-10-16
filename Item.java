package org.sample.xmlreader.core.models;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType; 
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;
/**
* @author Srinivasan
*
*/
@XmlRootElement(name = "Channel") 
@XmlAccessorType(XmlAccessType.FIELD)
public class Item implements Serializable {
 /**
* Generated serialVersionUID
*/
private static final long serialVersionUID = -2525852155658067914L;
@XmlElement(name = "Title") 
private String title;
@XmlElement(name = "Desc") 
private String desc;
@XmlElement(name = "Link") 
private String link;
@XmlElement(name = "Product_URL") 
private String productUrl;
@XmlElement(name = "GUID") 
private double guid;
@XmlElement(name = "Published_Date")
 private String pubDate;
public String getTitle() { 
return title;
}
public void setTitle(String title) {
this.title = title;
}
public String getDesc() { 
return desc;
}
public void setDesc(String desc) { 
this.desc = desc;
}
public String getLink() { 
return link;
}
public void setLink(String link) { 
this.link = link;
}
public String getProductUrl() { 
return productUrl;
}
public void setProductUrl(String productUrl) { 
this.productUrl = productUrl;
}
public double getGuid() { 
return guid;
}
public void setGuid(double guid) { 
this.guid = guid;
}
public String getPubDate() { 
return pubDate;
}
public void setPubDate(String pubDate) { 
this.pubDate = pubDate;
}
public double getSourceUrl() { 
return sourceUrl;
}
public void setSourceUrl(double sourceUrl) { 
this.sourceUrl = sourceUrl;
}
@XmlElement(name = "Source_Url") 
private double sourceUrl;
}
