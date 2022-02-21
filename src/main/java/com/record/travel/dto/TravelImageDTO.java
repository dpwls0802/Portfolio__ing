package com.record.travel.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelImageDTO  {
	
	private String imageName;
	private String uuid;
	private String folderPath;
	
	public String getImageURL() {
		try {
			return URLEncoder.encode(folderPath+"/"+uuid+"_"+ imageName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(folderPath+"/s_"+uuid+"_" + imageName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
