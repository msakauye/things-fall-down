package com.github.thingsfalldown;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import android.view.KeyEvent;

import com.github.thingsfalldown.manager.ResourceManager;
import com.github.thingsfalldown.manager.SceneManager;

/**
 * The main activity for our game, Things Fall Down.
 * Things Fall Down is a platform style game where users manipulate gravity
 * in order to solve puzzles.
 * <p>
 * Our game engine is AndEngine (http://www.andengine.org/), an open source
 * Android 2D OpenGL game engine developed by Nicholas Gramlich.
 * <p>
 * This game was created by Mark Sakauye and Susana Fong in 2014.
 * <p>
 * Follow our progress on our blog: http://thingsfalldown.wordpress.com/
 * 
 */
public class ThingsFallDownActivity extends BaseGameActivity {
	
	private BoundCamera camera;
	private ResourceManager resourceManager;
	
	private static final int SCREEN_SIZE_X = 800;
	private static final int SCREEN_SIZE_Y = 480;
	
	/* (non-Javadoc)
	 * @see org.andengine.ui.IGameInterface#onCreateEngineOptions()
	 */
	@Override
	public EngineOptions onCreateEngineOptions() {
		camera = new BoundCamera (0, 0, SCREEN_SIZE_X, SCREEN_SIZE_Y);
		EngineOptions engineOptions = new EngineOptions (true, 
				ScreenOrientation.LANDSCAPE_FIXED, 
				new RatioResolutionPolicy(SCREEN_SIZE_X, SCREEN_SIZE_Y), 
				this.camera);
		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engineOptions;
	}
	
	/* (non-Javadoc)
	 * @see org.andengine.ui.activity.BaseGameActivity#onCreateEngine(org.andengine.engine.options.EngineOptions)
	 */
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		return new LimitedFPSEngine (pEngineOptions, 60);
	}

	/* (non-Javadoc)
	 * @see org.andengine.ui.IGameInterface#onCreateResources(org.andengine.ui.IGameInterface.OnCreateResourcesCallback)
	 */
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws IOException {
		ResourceManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
		resourceManager = ResourceManager.getInstance();
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	/* (non-Javadoc)
	 * @see org.andengine.ui.IGameInterface#onCreateScene(org.andengine.ui.IGameInterface.OnCreateSceneCallback)
	 */
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws IOException {
		SceneManager.getInstance().createLogoScreen(pOnCreateSceneCallback);
	}

	/* (non-Javadoc)
	 * @see org.andengine.ui.IGameInterface#onPopulateScene(org.andengine.entity.scene.Scene, org.andengine.ui.IGameInterface.OnPopulateSceneCallback)
	 */
	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback)
			throws IOException {
		mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
			public void onTimePassed(final TimerHandler pTimerHandler) {
				mEngine.unregisterUpdateHandler(pTimerHandler);
				// load menu resources, create menu scene
				// set menu scene using scene manager
				// disposeSplashScene();
				SceneManager.getInstance().createMenuScene();
			}
		}));
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.andengine.ui.activity.BaseGameActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
}
