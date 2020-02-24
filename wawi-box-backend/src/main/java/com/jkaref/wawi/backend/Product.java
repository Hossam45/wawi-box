package com.jkaref.wawi.backend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="wawi-box-products")
public class Product {

	@Id
	public String id;
	public org.bson.Document json;

    public Product(){}
	public Product(String json) {
		this.json = org.bson.Document.parse(json);
	}

	public Product(org.bson.Document doc) {
		this.json = doc;
	}

	public String getId() {
		return id;
	}

	public String getJson() {
		return json.toJson();
	}

	@Override
	public String toString() {
		return String.format("Product[id=%s, json=%s]", id, json.toString());
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((json == null) ? 0 : json.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (json == null) {
			if (other.json != null)
				return false;
		} else if (!json.equals(other.json))
			return false;
		return true;
	}
	
	

}

