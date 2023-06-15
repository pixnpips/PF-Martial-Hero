package Model;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Spritefactory {
    static String pathtoSprite1 = "/images/sprites/char1/";
    static String pathtoSprite2 = "/images/sprites/char2/";

    static ArrayList<String> imgFolderSprites = new ArrayList<String>();

    public Spritefactory() {
        fillList();
    }

    private void fillList() {
        imgFolderSprites.add("Idle");
        imgFolderSprites.add("Run_");
        imgFolderSprites.add("Attack1");
        imgFolderSprites.add("Attack2_");
        imgFolderSprites.add("Take Hit_");
        imgFolderSprites.add("Death_");
        imgFolderSprites.add("Jump_");
    }

    private static Sprite constructSprite(int num) {
        if (num == 1) {
            return new Sprite("Player_1", pathtoSprite1, imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0));
        } else {
            return new Sprite("Player_1", pathtoSprite2, imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0), imgFolderSprites.get(0));
        }
    }
}
