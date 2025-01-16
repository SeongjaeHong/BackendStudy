package oop1;

public class MusicPlayerMain1 {
    public static void main(String[] args) {
        int volume = 0;
        boolean isOn = false;

        // Turn on music player
        isOn = true;
        System.out.println("Start music player.");

        // Volume up
        volume++;
        System.out.println("Volume: " + volume);

        // Volume down
        volume--;
        System.out.println("Volue: " + volume);

        // Volume status
        if (isOn) {
            System.out.println("Music player On, Volume: " + volume);
        }
        else {
            System.out.println("Music player Off");
        }

        // Off music player
        isOn = false;
        System.out.println("Finish music player");
    }
}
