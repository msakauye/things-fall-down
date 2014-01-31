package com.github.thingsfalldown.template;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;

import com.github.thingsfalldown.manager.ResourceManager;
import com.github.thingsfalldown.manager.SceneManager.SceneType;

/**
 * The TemplateScene class is the abstract class defining what all necessary
 * methods for our scenes.
 */
public abstract class TemplateScene extends Scene {
	protected Engine engine;
	protected Activity activity;
	protected ResourceManager resourceManager;
	protected VertexBufferObjectManager vbom;
	protected BoundCamera camera;
	
	protected static final int SCREEN_SIZE_X = 800;
	protected static final int SCREEN_SIZE_Y = 480;
	
	/**
	 * Default constructor for our scene.
	 * Sets all required fields.
	 */
	public TemplateScene() {
		this.resourceManager = ResourceManager.getInstance();
		this.engine = resourceManager.engine;
		this.activity = resourceManager.activity;
		this.vbom = resourceManager.vbom;
		this.camera = resourceManager.camera;
		createScene();
	}
	
	/**
	 * Do everything we need to set our scene up in this method.
	 */
	public abstract void createScene();
	
	/**
	 * Define how to react when the back key is pressed.
	 * For example, going to a different screen or exiting the game.
	 */
	public abstract void onBackKeyPressed();
	
	/**
	 * @return the scene type, such as menu or game.
	 */
	public abstract SceneType getSceneType();
	
	/**
	 * Handle destroying our resources when we're done with them.
	 */
	public abstract void disposeScene();
}
