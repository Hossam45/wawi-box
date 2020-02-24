
package com.jkaref.wawi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jkaref.wawi.api.model.Data;



@JsonIgnoreProperties(ignoreUnknown = true)
public class DataJSON extends Data {
}
