package net.grace.engine.textures;

import net.grace.engine.ResourceLoader;

public class ModelTexture {
	
	private int textureID;

	public ModelTexture(int textureID) {
		this.textureID = textureID;
	}
	
	public static ModelTexture create(String asset) {
		return new ModelTexture(ResourceLoader.loadTexture(asset));
	}

	public void setTextureID(int textureID) {
		if(this.textureID != textureID) {
			this.textureID = textureID;
		}
	}
	
	public int getTextureID() {
		return textureID;
	}
}
