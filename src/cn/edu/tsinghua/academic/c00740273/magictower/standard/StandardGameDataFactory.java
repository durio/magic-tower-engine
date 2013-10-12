package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameRenderer;

public class StandardGameDataFactory {

	protected String data;
	protected Map<String, StandardTile> tileValues;
	protected StandardGameData gameData;
	protected StandardEvent firstEvent;
	protected GameRenderer renderer;

	public StandardGameDataFactory(String data) {
		this.data = data;
		this.tileValues = new HashMap<String, StandardTile>();
	}

	public void make() throws DataFormatException {
		Map<String, Object> attributes;
		StandardTile[][][] tiles;
		Coordinate startCoord;
		GameRenderer renderer;
		List<String> failureAttributeChecks;
		List<String> successAttributeChecks;
		try {
			JSONObject dataRoot = new JSONObject(this.data);

			tiles = this.makeTiles(dataRoot);
			attributes = this.makeAttributes(dataRoot);
			startCoord = this.makeStartCoord(dataRoot, tiles);
			failureAttributeChecks = this.makeStringList(dataRoot,
					"failure-checks");
			successAttributeChecks = this.makeStringList(dataRoot,
					"success-checks");

			JSONObject dataRenderer = dataRoot.getJSONObject("renderer");
			renderer = ClassUtils.makeRenderer(dataRenderer);
		} catch (JSONException e) {
			throw new DataFormatException(e);
		}
		this.gameData = new StandardGameData(startCoord, attributes, tiles);
		this.gameData.failureAttributeChecks = failureAttributeChecks;
		this.gameData.successAttributeChecks = successAttributeChecks;
		this.firstEvent = new StandardEvent(startCoord);
		this.renderer = renderer;
	}

	protected StandardTile[][][] makeTiles(JSONObject dataRoot)
			throws JSONException, DataFormatException {
		JSONObject dataSize = dataRoot.getJSONObject("size");
		int sizeZ = dataSize.getInt("z");
		int sizeX = dataSize.getInt("x");
		int sizeY = dataSize.getInt("y");
		if (sizeZ <= 0 || sizeX <= 0 || sizeY <= 0) {
			throw new DataFormatException("Invalid size");
		}

		StandardTile[][][] tiles = new StandardTile[sizeZ][sizeX][sizeY];
		JSONArray dataTiles = dataRoot.getJSONArray("tiles");
		JSONObject dataTileValues = dataRoot.getJSONObject("tile-values");
		for (int z = 0; z < sizeZ; z++) {
			JSONArray dataTilesZ = dataTiles.getJSONArray(z);
			for (int x = 0; x < sizeX; x++) {
				JSONArray dataTilesX = dataTilesZ.getJSONArray(x);
				for (int y = 0; y < sizeY; y++) {
					String tileId = dataTilesX.getString(y);
					tiles[z][x][y] = this.getTile(tileId, dataTileValues);
				}
			}
		}
		return tiles;
	}

	protected Map<String, Object> makeAttributes(JSONObject dataRoot)
			throws JSONException, DataFormatException {
		Map<String, Object> attributes = new HashMap<String, Object>();
		JSONObject dataAttributes = dataRoot.getJSONObject("attributes");
		@SuppressWarnings("unchecked")
		Iterator<String> attributeKeyIterator = dataAttributes.keys();
		while (attributeKeyIterator.hasNext()) {
			String attributeName = attributeKeyIterator.next();
			Object attributeValue = dataAttributes.get(attributeName);
			attributes.put(attributeName, attributeValue);
		}
		return attributes;
	}

	protected Coordinate makeStartCoord(JSONObject dataRoot,
			StandardTile[][][] tiles) throws JSONException, DataFormatException {
		JSONObject dataStart = dataRoot.getJSONObject("start");
		int startZ = dataStart.getInt("z");
		int startX = dataStart.getInt("x");
		int startY = dataStart.getInt("y");
		if (startZ < 0 || startX < 0 || startY < 0 || startZ >= tiles.length
				|| startX >= tiles[0].length || startY >= tiles[0][0].length) {
			throw new DataFormatException("Invalid starting point");
		}
		return new Coordinate(startZ, startX, startY);
	}

	protected List<String> makeStringList(JSONObject dataRoot, String location)
			throws JSONException, DataFormatException {
		JSONArray dataArray = dataRoot.getJSONArray(location);
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < dataArray.length(); i++) {
			arrayList.add(dataArray.getString(i));
		}
		return arrayList;
	}

	public StandardTile getTile(String tileId, JSONObject dataTileValues)
			throws JSONException, DataFormatException {
		if (this.tileValues.containsKey(tileId)) {
			return this.tileValues.get(tileId);
		}
		JSONObject dataTileValue = dataTileValues.getJSONObject(tileId);
		StandardTile tile = ClassUtils.makeTile(dataTileValue);
		this.tileValues.put(tileId, tile);
		return tile;
	}

	public StandardEvent makeFirstEvent() throws DataFormatException {
		if (this.firstEvent == null) {
			this.make();
		}
		return this.firstEvent;
	}

	public StandardGameData makeGameData() throws DataFormatException {
		if (this.gameData == null) {
			this.make();
		}
		return this.gameData;
	}

	public GameRenderer makeRenderer() throws DataFormatException {
		if (this.renderer == null) {
			this.make();
		}
		return this.renderer;
	}
}
