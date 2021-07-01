package _02_cat_facts_API.data_transfer_objects;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Result {

@SerializedName("data")
@Expose
private String[] data;

public String[] getData() {
return data;
}

public void setBaz(String[] data) {
this.data = data;
}

}