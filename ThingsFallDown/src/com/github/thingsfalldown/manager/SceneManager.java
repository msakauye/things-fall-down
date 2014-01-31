package com.github.thingsfalldown.manager;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.github.thingsfalldown.scene.GameScene;
import com.github.thingsfalldown.scene.LoadingScene;
import com.github.thingsfalldown.scene.MainMenuScene;
import com.github.thingsfalldown.scene.LogoScene;
import com.github.thingsfalldown.template.TemplateScene;

/**
 * The SceneManager class handles switching between all of our screens,
 * including loading and disposing of all screen resources.
 */
public class SceneManager {
	private TemplateScene logoScene;
	private TemplateScene menuScene;
	private TemplateScene gameScene;
	private TemplateScene loadingScene;
	
	private static final SceneManager INSTANCE = new SceneManager();
	private SceneType currentSceneType = SceneType.SCENE_LOGO;
	private TemplateScene currentScene;
	private Engine engine = ResourceManager.getInstance().engine;
	
	public enum SceneType {
		SCENE_LOGO,
		SCENE_MENU,
		SCENE_GAME,
		SCENE_LOADING
	}
	
	/**
	 * Set the current scene by relying on the child class's scene type
	 * 
	 * @param scene  the generic scene
	 */
	public void setScene(TemplateScene scene) {
		engine.setScene(scene);
		currentScene = scene;
		currentSceneType = scene.getSceneType();
	}
	
	/**
	 * Set our scene to the given SceneType enum
	 * 
	 * @param sceneType  the scene to set
	 */
	public void setScene(SceneType sceneType) {
		switch (sceneType) {
			case SCENE_MENU:
				setScene(menuScene);
				break;
			case SCENE_GAME:
				setScene(gameScene);
				break;
			case SCENE_LOGO:
				setScene(logoScene);
				break;
			case SCENE_LOADING:
				setScene(loadingScene);
				break;
			default:
				break;
		}
	}
	
	/**
	 * @return the singleton scene manager
	 */
	public static SceneManager getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @return the currently assigned scene type
	 */
	public SceneType getCurrentSceneType() {
		return currentSceneType;
	}
	
	/**
	 * @return the current scene
	 */
	public TemplateScene getCurrentScene() {
		return currentScene;
	}
	
	/**
	 * Load the logo screen resources and create the screen.
	 * 
	 * @param pOnCreateSceneCallback
	 */
	public void createLogoScreen(OnCreateSceneCallback pOnCreateSceneCallback) {
		ResourceManager.getInstance().loadLogoResources();
		logoScene = new LogoScene();
		currentScene = logoScene;
		pOnCreateSceneCallback.onCreateSceneFinished(logoScene);
	}
	
	/**
	 * Destroy all the logo sceen resources.
	 */
	private void disposeLogoScene() {
		ResourceManager.getInstance().unloadLogoScreen();
		logoScene.disposeScene();
		logoScene = null;
	}
	
	/**
	 * Load menu and loading screen resources, and create the scenes.
	 * We create the loading screen so that we may quickly switch to it once
	 * we click a button.
	 */
	public void createMenuScene() {
		ResourceManager.getInstance().loadMenuResources();
		menuScene = new MainMenuScene();
		loadingScene = new LoadingScene();
		setScene(menuScene);
		disposeLogoScene();
	}
	
	/**
	 * Show the loading screen while we dispose of existing scenes and show the menu.
	 * 
	 * @param mEngine
	 */
	public void loadMenuScene(final Engine mEngine) {
		setScene(loadingScene);
		gameScene.disposeScene();
		ResourceManager.getInstance().unloadGameTextures();
		mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
			public void onTimePassed(final TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				ResourceManager.getInstance().loadMenuTextures();
				setScene(menuScene);
			}
		}));
	}
	
	/**
	 * Show the loading screen while we dispose of existing scenes and show the game.
	 * 
	 * @param mEngine
	 */
	public void loadGameScene(final Engine mEngine) {
		setScene(loadingScene);
		ResourceManager.getInstance().unloadMenuTextures();
		mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
			public void onTimePassed(final TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				ResourceManager.getInstance().loadGameResources();
				gameScene = new GameScene();
				setScene(gameScene);
			}
		}));
	}
}
