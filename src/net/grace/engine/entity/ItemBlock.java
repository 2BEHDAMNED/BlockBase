package net.grace.engine.entity;

import org.lwjgl.util.vector.Vector3f;

import net.grace.engine.inventory.Item;
import net.grace.engine.models.CubeModel;
import net.grace.engine.models.TexturedModel;
import net.grace.engine.renderers.MasterRenderer;
import net.grace.engine.textures.ModelTexture;
import net.grace.engine.world.blocks.Block;

public class ItemBlock extends ItemEntity {

	public ItemBlock(Block block, Vector3f position) {
		super(Item.blockToItem(block), new TexturedModel(CubeModel.getRawModel(block), new ModelTexture(MasterRenderer.currentTexturePack.getTextureID())), new Vector3f(position));
		float scale = this.getScale();
		this.setSize(scale, scale);
	}
}
