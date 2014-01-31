package com.github.thingsfalldown.manager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

import com.github.thingsfalldown.ThingsFallDownActivity;

/**
 * The ResourceManager class, surprisingly, manages our resources.
 * It handles loading and disposing of our entities.
 */
public class ResourceManager {
	private static final ResourceManager INSTANCE = new ResourceManager();
	
	public Engine engine;
	public ThingsFallDownActivity activity;
	public BoundCamera camera;
	public VertexBufferObjectManager vbom;
	
	// Fonts that we use in our logo and menu scenes
	public Font bigfont = null;
	public Font littlefont = null;

	// Menu scene items
	public ITextureRegion menu_background_region;
	public ITextureRegion play_region;
	public ITextureRegion level_editor_region;
	public ITextureRegion settings_region;
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	

	/**
	 * Load all resources for our logo screen.
	 */
	public void loadLogoResources() {
		loadLogoFonts();
	}
	
	/**
	 * Load the large font for our logo screen if not loaded already.
	 */
	public void loadLogoFonts() {
		if (bigfont == null) {
			FontFactory.setAssetBasePath("font/");
			final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			
			bigfont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "Rolling_No_One.ttf", 50f, true, Color.WHITE.getARGBPackedInt(), 2f, Color.BLACK.getARGBPackedInt());
			bigfont.load();
		}
	}
	
	/**
	 * Dispose of the logo screen resources.
	 */
	public void unloadLogoScreen() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Load the graphics, fonts and audio for the menu screen.
	 */
	public void loadMenuResources() {
		loadMenuGraphics();
		loadMenuAudio();
		loadMenuFonts();
	}

	/**
	 * Load the background and button textures for the menu screen.
	 */
	private void loadMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "background.png");
		play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "play_button.png");
		level_editor_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "level_editor_button.png");
		settings_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "settings_button.png");
		
		try {
			this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.menuTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		} 
	}

	/**
	 * Load the audio for our menu screen.
	 */
	private void loadMenuAudio() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Load our menu fonts if not already loaded.
	 */
	private void loadMenuFonts() {
		FontFactory.setAssetBasePath("font/");
		
		if (bigfont == null) {
			final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	
			bigfont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "Rolling_No_One.ttf", 50f, true, Color.WHITE.getARGBPackedInt(), 2f, Color.BLACK.getARGBPackedInt());
			bigfont.load();
		}

		if (littlefont == null) {
			final ITexture subtitleFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			
			littlefont = FontFactory.createStrokeFromAsset(activity.getFontManager(), subtitleFontTexture, activity.getAssets(), "Rolling_No_One.ttf", 30f, true, Color.WHITE.getARGBPackedInt(), 1f, Color.BLACK.getARGBPackedInt());
			littlefont.load();
		}
	}
	
	/**
	 * Load the textures for the menu screen.
	 */
	public void loadMenuTextures() {
		menuTextureAtlas.load();
	}
	
	/**
	 * Dispose of the menu screen textures.
	 */
	public void unloadMenuTextures() {
		menuTextureAtlas.unload();
	}
	
	/**
	 * Load the graphics, fonts and audio for the game screen.
	 */
	public void loadGameResources() {
		loadGameGraphics();
		loadGameFonts();
		loadGameAudio();
	}

	/**
	 * Load the graphics for our game screen.
	 */
	private void loadGameGraphics() {
		// TODO load our game graphics
	}

	/**
	 * Load the fonts for our game screen.
	 */
	private void loadGameFonts() {
		// TODO Auto-generated method stub
	}

	/**
	 * Load the audio for our game screen.
	 */
	private void loadGameAudio() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Dispose of the game screen textures.
	 */
	public void unloadGameTextures() {
		// TODO Auto-generated method stub
	}
	
	/**
     * Set up our resource manager properties.
     * 
     * @param engine	
     * @param activity	
     * @param camera	
     * @param vbom		
     */
	public static void prepareManager (Engine engine, ThingsFallDownActivity activity, BoundCamera camera, VertexBufferObjectManager vbom) {
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}
	
	/**
	 * @return the singleton resource manager
	 */
	public static ResourceManager getInstance() {
		return INSTANCE;
	}
}
