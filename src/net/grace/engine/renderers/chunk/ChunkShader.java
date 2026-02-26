package net.grace.engine.renderers.chunk;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import net.grace.engine.entity.Camera;
import net.grace.engine.renderers.ShaderProgram;
import net.grace.toolbox.Maths;

public class ChunkShader extends ShaderProgram {

	private static String vertexFile = "/net/grace/engine/renderers/chunk/chunkVertex.glsl";
	private static String fragmentFile = "/net/grace/engine/renderers/chunk/chunkFragment.glsl";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_skyColour;
	
	public ChunkShader() {
		super(vertexFile, fragmentFile);	
	}
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_skyColour = super.getUniformLocation("skyColour");
	}
	
	public void loadSkyColour(float r, float g, float b) {
		super.load3DVector(location_skyColour, new Vector3f(r,g,b));
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
	}
}
