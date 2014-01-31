package com.github.thingsfalldown.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.util.GLState;

import com.github.thingsfalldown.manager.SceneManager.SceneType;
import com.github.thingsfalldown.template.TemplateScene;

/**
 * MainMenuScene is our game menu.
 */
public class MainMenuScene extends TemplateScene implements IOnMenuItemClickListener {
	private MenuScene menuChildScene;
	private final int MENU_PLAY = 0;
	private final int MENU_LEVEL_EDITOR = 1;
	private final int MENU_SETTINGS = 2;

	/* (non-Javadoc)
	 * @see com.github.thingsfalldown.template.TemplateScene#createScene()
	 */
	@Override
	public void createScene() {
		// Create our background
		attachChild(new Sprite(SCREEN_SIZE_X / 2, SCREEN_SIZE_Y / 2, resourceManager.menu_background_region, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState,  pCamera);
				pGLState.enableDither();
			}
		});
		
		// Create our title
		attachChild(new Text(SCREEN_SIZE_X / 2, SCREEN_SIZE_Y - 50, resourceManager.bigfont, "Things Fall Down!", vbom));
		attachChild(new Text(SCREEN_SIZE_X / 2, SCREEN_SIZE_Y - 100, resourceManager.littlefont, "The Game", vbom));
		
		// Create our buttons
		menuChildScene = new MenuScene(camera);
		menuChildScene.setPosition(0, 0);
		
		final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourceManager.play_region, vbom), 1.2f, 1);
		final IMenuItem levelEditorMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_LEVEL_EDITOR, resourceManager.level_editor_region, vbom), 1.2f, 1);
		final IMenuItem settingsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SETTINGS, resourceManager.settings_region, vbom), 1.2f, 1);
		
		menuChildScene.addMenuItem(playMenuItem);
		menuChildScene.addMenuItem(levelEditorMenuItem);
		menuChildScene.addMenuItem(settingsMenuItem);
		
		menuChildScene.buildAnimations();
		menuChildScene.setBackgroundEnabled(false);
		
		playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() - 30);
		levelEditorMenuItem.setPosition(levelEditorMenuItem.getX(), levelEditorMenuItem.getY() - 55);
		settingsMenuItem.setPosition(settingsMenuItem.getX(), settingsMenuItem.getY() - 80);
		
		menuChildScene.setOnMenuItemClickListener(this);
		
		setChildScene(menuChildScene);
	}

	/* (non-Javadoc)
	 * @see com.github.thingsfalldown.template.TemplateScene#onBackKeyPressed()
	 */
	@Override
	public void onBackKeyPressed() {
		System.exit(0);
	}
	
	/* (non-Javadoc)
	 * @see com.github.thingsfalldown.template.TemplateScene#getSceneType()
	 */
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}

	/* (non-Javadoc)
	 * @see com.github.thingsfalldown.template.TemplateScene#disposeScene()
	 */
	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener#onMenuItemClicked(org.andengine.entity.scene.menu.MenuScene, org.andengine.entity.scene.menu.item.IMenuItem, float, float)
	 */
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case MENU_PLAY:
			return true;
		case MENU_LEVEL_EDITOR:
			return true;
		case MENU_SETTINGS:
			return true;
		default:
			return false;
		}
	}

}
