package teste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarvinMusicAnalyzer {
	
	public static int[] getRareNotes(int[] notes) {
        Map<Integer, Integer> noteFrequency = new HashMap<>();

        // Count the frequency of each note
        for (int note : notes) {
            noteFrequency.put(note, noteFrequency.getOrDefault(note, 0) + 1);
        }

        // Find the rare notes
        List<Integer> rareNotes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : noteFrequency.entrySet()) {
            if (entry.getValue() == 1) {
                rareNotes.add(entry.getKey());
            }
        }

        // Convert the List<Integer> to int[]
        if (rareNotes.isEmpty()) {
            return null;
        } else {
            int[] rareNotesArray = new int[rareNotes.size()];
            for (int i = 0; i < rareNotes.size(); i++) {
                rareNotesArray[i] = rareNotes.get(i);
            }
            return rareNotesArray;
        }
    }

    public static void main(String[] args) {
        int[] notes = {1, 2, 3, 2, 2, 1, 5, 5};
        int[] rareNotes = getRareNotes(notes);

        if (rareNotes == null) {
            System.out.println("No rare notes in the song.");
        } else {
            System.out.print("Rare notes: ");
            for (int note : rareNotes) {
                System.out.print(note + " ");
            }
            System.out.println();
        }
    }

}
