package test;

import javafx.scene.input.KeyCode;
import olcPGEApproach.AbstractGame;
import olcPGEApproach.GameContainer;
import olcPGEApproach.gfx.images.ImageTile;
import olcPGEApproach.vectors.points2d.Vec2di;
import retromenu.MenuManager;
import retromenu.MenuObject;

public class RetroMenuGame implements AbstractGame {

    private ImageTile gfx;

    private MenuObject mo;

    private MenuManager mm;

    @Override
    public void initialize(GameContainer gc) {
        gfx = new ImageTile("/RetroMenu2.png", 16, 24);

        mm = new MenuManager();

        mo = new MenuObject("main").setTable(2, 3);
        mo.add("Attack").setId(101);

        mo.add("Magic").setTable(1, 2);

        mo.add("Magic").add("White").setTable(3, 6);
        MenuObject menuWhiteMagic = mo.add("Magic").add("White");
        menuWhiteMagic.add("Cure").setId(401);
        menuWhiteMagic.add("Cura").setId(402);
        menuWhiteMagic.add("Curaga").setId(403);
        menuWhiteMagic.add("Esuna").setId(404);

        mo.add("Magic").add("Black").setTable(3, 4);
        MenuObject menuBlackMagic = mo.add("Magic").add("Black");
        menuBlackMagic.add("Fire").setId(201);
        menuBlackMagic.add("Fira").setId(202);
        menuBlackMagic.add("Firaga").setId(203);
        menuBlackMagic.add("Blizzard").setId(204);
        menuBlackMagic.add("Blizzara").setId(205).setEnabled(false);
        menuBlackMagic.add("Blizzaga").setId(206).setEnabled(false);
        menuBlackMagic.add("Thunder").setId(207);
        menuBlackMagic.add("Thundara").setId(208);
        menuBlackMagic.add("Thundaga").setId(209);
        menuBlackMagic.add("Quake").setId(210);
        menuBlackMagic.add("Quake2").setId(211);
        menuBlackMagic.add("Quake3").setId(212);
        menuBlackMagic.add("Bio").setId(213);
        menuBlackMagic.add("Bio1").setId(214);
        menuBlackMagic.add("Bio2").setId(215);
        menuBlackMagic.add("Demi").setId(216);
        menuBlackMagic.add("Demi1").setId(217);
        menuBlackMagic.add("Demi2").setId(218);

        mo.add("Defend").setId(102);

        mo.add("Items").setTable(2, 4).setEnabled(false);
        mo.add("Items").add("Potion").setId(301);
        mo.add("Items").add("Ether").setId(302);
        mo.add("Items").add("Elixir").setId(303);

        mo.add("Escape").setId(103);

        mo.build();

        mm.open(mo);
    }

    @Override
    public void update(GameContainer gc, float elapsedTime) {
        if (gc.getInput().isKeyUp(KeyCode.M)) {
            mm.open(mo);
        }
        if (gc.getInput().isKeyUp(KeyCode.UP)) {
            mm.onUp();
        }
        if (gc.getInput().isKeyUp(KeyCode.DOWN)) {
            mm.onDown();
        }
        if (gc.getInput().isKeyUp(KeyCode.LEFT)) {
            mm.onLeft();
        }
        if (gc.getInput().isKeyUp(KeyCode.RIGHT)) {
            mm.onRight();
        }
        if (gc.getInput().isKeyUp(KeyCode.Z)) {
            mm.onBack();
        }
        if (gc.getInput().isKeyUp(KeyCode.ESCAPE)) {
            mm.clear();
        }
        if (gc.getInput().isKeyUp(KeyCode.D)) {
            mm.setDrawTileBorders(!mm.isDrawingTileBorders());
        }

        MenuObject command = null;

        if (gc.getInput().isKeyUp(KeyCode.SPACE)) {
            command = mm.onConfirm();
        }

        if (command != null) {
            System.out.println("Selected: " + command.getName() + " ID: " + command.getId());
            if (!command.hasChildren()) {
                mm.clear();
            }
        }

    }

    @Override
    public void render(GameContainer gc) {
        gc.getRenderer().clear(0xffF7CAE7);
        mm.draw(gc.getRenderer(), gfx, new Vec2di(100, 100));
    }

}
