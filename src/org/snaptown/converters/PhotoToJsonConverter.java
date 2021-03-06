package org.snaptown.converters;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.snaptown.models.Photo;

public class PhotoToJsonConverter implements IConverter<Photo, JSONObject> {

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject convert(Photo photo) throws Exception {
		JSONObject photoJsonObj = new JSONObject();
		photoJsonObj.put("id", photo.getId());
		photoJsonObj.put("creator", photo.getUserId());
		photoJsonObj.put("longitude", photo.getLongitude());
		photoJsonObj.put("latitude", photo.getLatitude());
		photoJsonObj.put("comment", photo.getComment());
		photoJsonObj.put("imgPath", photo.getImgPath());
		photoJsonObj.put("date", photo.getDateCreated().getDate());
		return photoJsonObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject convert(List<Photo> photos) throws Exception {
		JSONObject jsonResult = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		for (Photo photo : photos) {
			JSONObject photoJsonObj = convert(photo);
			jsonArr.add(photoJsonObj);
		}
		jsonResult.put("photos", jsonArr);
		return jsonResult;
	}
}
