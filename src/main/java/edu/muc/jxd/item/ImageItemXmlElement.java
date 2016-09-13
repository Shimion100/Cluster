package edu.muc.jxd.item;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwd on 9/13/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Image")
@XmlType(propOrder = {
        "id",
        "data"
})
public class ImageItemXmlElement {
    private long id;
    private String data;

    public ImageItemXmlElement(){
       // data=new ArrayList<>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
