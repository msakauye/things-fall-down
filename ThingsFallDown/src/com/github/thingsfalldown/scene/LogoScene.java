package com.github.thingsfalldown.scene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

import com.github.thingsfalldown.manager.SceneManager.SceneType;
import com.github.thingsfalldown.template.TemplateScene;

/**
 * LogoScene is our logo(s) screen.
 */
public class LogoScene extends TemplateScene {

	/* (non-Javadoc)
	 * @see com.github.thingsfalldown.template.TemplateScene#createScene()
	 */
	@Override
	public void createScene() {
		setBackground(new Background(Color.BLACK));
		attachChild(new Text(SCREEN_SIZE_X / 2, SCREEN_SIZE_Y / 2, resourceManager.bigfont, "Mark Sakauye\nand\nSusana Fong\npresent...", vbom));
	}

	/* (non-Javadoc)
	 * @see com.github.thingsfalldown.template.TemplateScene#onBackKeyPressed()
	 */
	@Override
	public void onBackKeyPressed() {
		
	}
	
	/* (non-Javadoc)
	 * @see com.github.thingsfalldown.template.TemplateScene#getSceneType()
	 */
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_LOGO;
	}

	/* (non-Javadoc)
	 * @see com.github.thingsfalldown.template.TemplateScene#disposeScene()
	 */
	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

}
