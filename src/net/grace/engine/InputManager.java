package net.grace.engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import net.grace.engine.entity.Camera;
import net.grace.engine.entity.ItemBlock;
import net.grace.engine.renderers.MasterRenderer;
import net.grace.main.Main;
import net.grace.main.gui.GuiChat;
import net.grace.main.gui.GuiInventory;
import net.grace.main.gui.GuiOptions;
import net.grace.main.gui.GuiPauseMenu;

public class InputManager {

	private Camera camera;
	public static final int texturePackKey = Keyboard.KEY_F1;
	public static final int screenShotKey = Keyboard.KEY_F2;
	public static final int debugKey = Keyboard.KEY_F3;
	
	public static final int pauseEscapeKey = Keyboard.KEY_ESCAPE;
	
	public static final int refreshKey = Keyboard.KEY_F;
	public static final int inventoryKey = Keyboard.KEY_E;
	public static final int teleportKey = Keyboard.KEY_P;

	public static final int chatKey = Keyboard.KEY_T;
	public static final int itemKey = Keyboard.KEY_Q;
	
	public static boolean lockInPause = false;
	
	public static boolean lockInChangeTexture = false;
	public static boolean lockInScreenshot = false;
	public static boolean lockInDebug = false;
	
	public static boolean lockInRefresh = false;
	public static boolean lockInItem = false;
	
	private Vector3f mul(Vector3f one, Vector3f two) {
		return new Vector3f(one.x*two.x, one.y*two.y, one.z*two.z);
	}

	public void handleInput() {
		if(!Main.isPaused()) {
			
			if(Main.inGameGUI != null) {
				if(Keyboard.isKeyDown(itemKey)) {
					if(!lockInItem) {
						if(Main.inGameGUI.getSelectedItem() != null) {
							Vector3f playerPos = new Vector3f(Main.thePlayer.getPosition());
							playerPos.setY(playerPos.y + Main.thePlayer.heightOffset);
							ItemBlock block = new ItemBlock(Main.inGameGUI.getSelectedItem(), playerPos);
							block.setPosition(block.getRoundedPosition());
							System.out.println(block.getRoundedPosition() + " " + playerPos);
							block.setRotation(0,Main.thePlayer.getRotation().y-90,0);
							block.moveRelative(1, 0, 0.1f);
							Main.theWorld.addEntity(block);
							
							//Main.inGameGUI.setSelectedItem(null);
						}
						
					}
					lockInItem = true;
				} else {
					lockInItem = false;
				}
			}
			
			
			if(!(Main.currentScreen instanceof GuiChat)) {
				if(Keyboard.isKeyDown(teleportKey)) {
					Main.thePlayer.resetPos();
				}
				
				if(Keyboard.isKeyDown(refreshKey)) {
					if(!lockInRefresh) {
						Main.theWorld.refreshChunks();
					}

					lockInRefresh = true;
				} else {
					lockInRefresh = false;
				}

				if(Keyboard.isKeyDown(texturePackKey)) {
					if(!lockInChangeTexture) {
						int texture = MasterRenderer.currentTexturePack.getTextureID();
						if(texture == MasterRenderer.defaultTexturePack) {
							MasterRenderer.getInstance().setTexturePack(MasterRenderer.customTexturePack);
						} else {
							MasterRenderer.getInstance().setTexturePack(MasterRenderer.defaultTexturePack);
						}
					}
					lockInChangeTexture = true;
				} else {
					lockInChangeTexture = false;
				}
			}
			

			

			if(Keyboard.isKeyDown(debugKey)) {
				if(!lockInDebug) {
					Main.inGameGUI.toggleDebug();
				}
				lockInDebug = true;
			} else {
				lockInDebug = false;
			}

			
			if(Keyboard.isKeyDown(inventoryKey)) {
				if(Main.currentScreen == null) {
					Main.currentScreen = new GuiInventory();
				}
			}
			
			if(Main.theNetwork != null && Keyboard.isKeyDown(chatKey) && Main.currentScreen == null) {
				Main.currentScreen = new GuiChat();
			}
		}
		
		if(Main.inGameGUI != null) {
			if(Keyboard.isKeyDown(screenShotKey)) {
				if(!lockInScreenshot) {
					DisplayManager.saveScreenshot();
				}
				lockInScreenshot = true;
			} else {
				lockInScreenshot = false;
			}
		}
		
		if((Main.inGameGUI != null) && (Main.currentScreen instanceof GuiPauseMenu || Main.currentScreen instanceof GuiInventory || Main.currentScreen == null || Main.currentScreen instanceof GuiChat || Main.currentScreen instanceof GuiOptions)) {
			if(!lockInPause) {
				if(Keyboard.isKeyDown(pauseEscapeKey)) {
					if(Main.currentScreen != null) {
						if(Main.currentScreen instanceof GuiOptions)  {
							Main.currentScreen.prepareCleanUp();
							Main.currentScreen = new GuiPauseMenu();
						} else {
							Main.currentScreen.prepareCleanUp();
							Main.currentScreen = null;
						}
						
					} else {
						Main.currentScreen = new GuiPauseMenu();
					}
					lockInPause = true;
				}
			} else {
				if(!Keyboard.isKeyDown(pauseEscapeKey)) {
					lockInPause = false;
				}
			}
		}
		
	}
	
	public static boolean isMoving() {
		return Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_SPACE);
	}
	
	public static int lastMouseDX = Mouse.getDX();
	public static int lastMouseDY = Mouse.getDY();
	public static boolean hasMouseMoved() {
		int mouseDX = Mouse.getDX();
		int mouseDY = Mouse.getDX();
		if( mouseDX != lastMouseDX || mouseDY != lastMouseDY) {
			lastMouseDX = Mouse.getDX();
			lastMouseDY = Mouse.getDY();
			return true;
		}
		return false;
	}

}
