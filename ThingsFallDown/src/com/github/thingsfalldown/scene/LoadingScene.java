package com.github.thingsfalldown.scene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

import com.github.thingsfalldown.manager.SceneManager.SceneType;
import com.github.thingsfalldown.template.TemplateScene;

/**
 * LoadingScene is our loading screen we show in between screens.
 */
public class LoadingScene extends TemplateScene {

	@Override
	public void createScene() {
		setBackground(new Background(Color.BLACK));
		attachChild(new Text(SCREEN_SIZE_X / 2, SCREEN_SIZE_Y / 2, resourceManager.bigfont, "Mark Sakauye and Susana Font present...", vbom));
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_LOADING;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

}
