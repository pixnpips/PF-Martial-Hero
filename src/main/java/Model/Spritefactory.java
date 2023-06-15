package Model;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Spritefactory {
    static String pathtoSprite1 = "/images/sprites/char1/";
    static String pathtoSprite2 = "/images/sprites/char2/";

    static String imgFolderSprites[]= new String[]{"Idle_","Run_","Attack1_","Attack2_","Take Hit_","Death_","Jump_"};

    public Spritefactory() {
    }

    public static Sprite constructSprite(int num) {
        if (num == 1) {
            return new Sprite("Player_1", pathtoSprite1, imgFolderSprites[0], imgFolderSprites[1], imgFolderSprites[2], imgFolderSprites[3], imgFolderSprites[4], imgFolderSprites[5], imgFolderSprites[6]);
        } else if (num==2) {
            return new Sprite("Player_2", pathtoSprite2, imgFolderSprites[0], imgFolderSprites[1], imgFolderSprites[2], imgFolderSprites[3], imgFolderSprites[4], imgFolderSprites[5], imgFolderSprites[6]);
        }else return null;
    }
}
