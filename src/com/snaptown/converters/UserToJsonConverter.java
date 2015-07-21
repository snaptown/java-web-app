package com.snaptown.converters;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.snaptown.models.User;

public class UserToJsonConverter implements IConverter<User, JSONObject> {

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject convert(User user) throws Exception {
		JSONObject userJsonObj = new JSONObject();
		userJsonObj.put("username", user.getUsername());
		userJsonObj.put("password", user.getPassword());
		return userJsonObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject convert(List<User> users) throws Exception {
		JSONObject jsonResult = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		for (User user : users) {
			JSONObject userJsonObj = convert(user);
			jsonArr.add(userJsonObj);
		}
		jsonResult.put("users", jsonArr);
		return jsonResult;
	}

}
