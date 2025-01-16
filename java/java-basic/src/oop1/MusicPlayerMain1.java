package oop1;

public class MusicPlayerMain1 {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer(3,false);
        System.out.println(player.volume);
        System.out.println(player.isOn);
    }
}
