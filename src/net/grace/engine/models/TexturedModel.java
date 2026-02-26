package net.grace.engine.models;

import net.grace.engine.Loader;
import net.grace.engine.textures.ModelTexture;

public class TexturedModel {
	
	RawModel model;
	ModelTexture texture;

	public TexturedModel(RawModel model, ModelTexture texture) {
		this.model = model;
		this.texture = texture;
	}
	
	public TexturedModel(RawModel model, int texture) {
		this.model = model;
		this.texture = new ModelTexture(texture);
	}

	public RawModel getRawModel() {
		return model;
	}

	public ModelTexture getTexture() {
		return texture;
	}

	public void setRawModel(RawModel model) {
		if(this.model != null && this.model != model) {
			this.model.cleanUp();
			
		}
		
		this.model = model;
	}
}
