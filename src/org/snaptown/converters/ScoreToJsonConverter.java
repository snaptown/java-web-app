package org.snaptown.converters;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.snaptown.models.Score;

public class ScoreToJsonConverter implements IConverter<Score, JSONObject> {

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject convert(Score score) throws Exception {
		JSONObject scoreJsonObj = new JSONObject();
		scoreJsonObj.put("voter", score.getVoter().getUsername());
		scoreJsonObj.put("photo", score.getPhoto().getId());
		scoreJsonObj.put("isUpvote", score.isUpvote());
		return scoreJsonObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject convert(List<Score> scores) throws Exception {
		JSONObject jsonResult = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		for (Score score : scores) {
			JSONObject scoreJsonObj = convert(score);
			jsonArr.add(scoreJsonObj);
		}
		jsonResult.put("scores", jsonArr);
		return jsonResult;
	}
}
