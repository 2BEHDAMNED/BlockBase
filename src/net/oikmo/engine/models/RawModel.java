package net.oikmo.engine.models;

import net.oikmo.engine.Loader;

public class RawModel {
	
	int vaoID, vertexCount;
	int[] vboIDs;
	
	public RawModel(int vaoID, int[] vboIDs, int vertexCount) {
		this.vaoID = vaoID;
		this.vboIDs = vboIDs;
		this.vertexCount = vertexCount;
	}
	
	public void cleanUp() {
		Loader.cleanUpVAO(vaoID);
		for(int i : vboIDs) {
			Loader.cleanUpVBO(i);
		}
	}
	
	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}
