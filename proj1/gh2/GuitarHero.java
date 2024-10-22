package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    private GuitarString[] guitarString;
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public GuitarHero() {
        guitarString = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            guitarString[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12));
        }
    }

    public static void main(String[] args) {
        GuitarHero guitarHero = new GuitarHero();
        while (true) {

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    guitarHero.guitarString[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < 37; i++) {
                sample += guitarHero.guitarString[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++) {
                guitarHero.guitarString[i].tic();
            }
        }
    }
}
