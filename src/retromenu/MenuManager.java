package retromenu;

import olcPGEApproach.gfx.HexColors;
import olcPGEApproach.gfx.Renderer;
import olcPGEApproach.gfx.images.ImageTile;
import olcPGEApproach.vectors.points2d.Vec2di;

import java.util.Stack;

/**
 * This class manages the menu
 * A menu is formed by menu objects which contains
 * menu objects child
 * It manages all objects with an stack of panels
 * to see on the bottom the first menu (root) and
 * on top the newest menu (child)
 */
public class MenuManager {

    /**
     * This list is going to work as an stack
     */
    private final Stack<MenuObject> panels;

    /**
     * This is the offset between two panels
     * How much is offset the child panel from parent
     */
    private Vec2di offsetPanels = new Vec2di(10, 10);

    /**
     * Flag to sww the tiles borders: debugging
     */
    private boolean drawTileBorders = false;

    /**
     * Constructor
     */
    public MenuManager() {
        panels = new Stack<>();
    }

    /**
     * Opens a menu object
     */
    public void open(MenuObject mo) {
        clear();
        panels.add(mo);
    }

    /**
     * Clears all the stack
     */
    public void clear() {
        panels.clear();
    }

    // User Input methods

    public void onUp() {
        if (!panels.isEmpty()) {
            panels.peek().onUp();
        }
    }

    public void onDown() {
        if (!panels.isEmpty()) {
            panels.peek().onDown();
        }
    }

    public void onLeft() {
        if (!panels.isEmpty()) {
            panels.peek().onLeft();
        }
    }

    public void onRight() {
        if (!panels.isEmpty()) {
            panels.peek().onRight();
        }
    }

    public void onBack() {
        if (!panels.isEmpty()) {
            panels.pop();
        }
    }

    public MenuObject onConfirm() {
        if (panels.isEmpty()) {
            return null;
        }

        MenuObject next = panels.peek().onConfirm();

        if (next.equals(panels.peek())) {
            if (panels.peek().getSelectedItem().isEnabled()) {
                return panels.peek().getSelectedItem();
            }
        } else {
            if (next.isEnabled()) {
                return panels.push(next);
            }
        }

        return null;
    }

    private void drawCursorImage(Renderer r, ImageTile img, Vec2di screenPos) {
        r.drawImage(img.getTileImage(4, 0), screenPos.getX(), screenPos.getY());
        r.drawImage(img.getTileImage(5, 0), screenPos.getX() + img.getTileW(), screenPos.getY());
        r.drawImage(img.getTileImage(4, 1), screenPos.getX(), screenPos.getY() + img.getTileH());
        r.drawImage(img.getTileImage(5, 1), screenPos.getX() + img.getTileW(), screenPos.getY() + img.getTileH());
        if (drawTileBorders) {
            r.drawRect(screenPos.getX(), screenPos.getY(), img.getTileW() * 2, img.getTileH() * 2, HexColors.YELLOW);
        }
    }

    public void draw(Renderer r, ImageTile img, Vec2di screenOffset) {
        if (panels.isEmpty()) {
            return;
        }

        for (MenuObject p : panels) {
            p.drawSelf(r, img, screenOffset, drawTileBorders);
            screenOffset.add(offsetPanels);
        }

        // Draw cursor
        drawCursorImage(r, img, panels.get(panels.size() - 1).getCursorPos());
    }

    // Getters and Setters

    public Vec2di getOffsetPanels() {
        return offsetPanels;
    }

    public boolean isDrawingTileBorders() {
        return drawTileBorders;
    }

    public void setOffsetPanels(Vec2di offsetPanels) {
        this.offsetPanels = offsetPanels;
    }

    public void setDrawTileBorders(boolean drawTileBorders) {
        this.drawTileBorders = drawTileBorders;
    }

}
