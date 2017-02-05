import java.io.File;
import javax.sound.midi.*;

/**
 * Write a description of class midihub here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class midihub
{
    public static final int NOTE_ON = 144;
    public static final int NOTE_OFF = 128;
    public static final int command = 176;
    public static final String[] NOTE_NAMES ={"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    
    public static Sequence midisequence;
    public static Track[] tracks;
    
    public static void main(String args[]) throws Exception{
        loadMidi();
        printNotesInSequence();
    }
    
    static void loadMidi() throws Exception{
        midisequence = MidiSystem.getSequence(new File("mond_1.mid"));
        System.out.println("Length: "+midisequence.getMicrosecondLength());
        tracks = midisequence.getTracks();
    }
    
    static void printNotesInSequence(){
        int t0event = 0;
        int t1event = 0;
        int t2event = 0;
        int t3event = 0;
        int t4event = 0;
        int t5event = 0;
        int t6event = 0;
        int t7event = 0;
        
        MidiEvent event;
        MidiMessage message;
        int lastTick = maxTick();
        lastTick=10000; //for testing - generates a small amount of console output
        for(int tick=0; tick<lastTick; tick++){            
            if(tracks[0].size()>t0event && tracks[0].get(t0event).getTick()<=tick){
                message = tracks[0].get(t0event).getMessage();
                printNote(message,tick,0);
                t0event++;
            }
            if(tracks[1].size()>t1event && tracks[1].get(t1event).getTick()<=tick){
                message = tracks[1].get(t1event).getMessage();
                printNote(message,tick,1);
                t1event++;
            }
            if(tracks[2].size()>t2event && tracks[2].get(t2event).getTick()<=tick){
                message = tracks[2].get(t2event).getMessage();
                printNote(message,tick,2);
                t2event++;
            }
            if(tracks[3].size()>t3event && tracks[3].get(t3event).getTick()<=tick){
                message = tracks[3].get(t3event).getMessage();
                printNote(message,tick,3);
                t3event++;
            }
            if(tracks[4].size()>t4event && tracks[4].get(t4event).getTick()<=tick){
                message = tracks[4].get(t4event).getMessage();
                printNote(message,tick,4);
                t4event++;
            }
            if(tracks[5].size()>t5event && tracks[5].get(t5event).getTick()<=tick){
                message = tracks[5].get(t5event).getMessage();
                printNote(message,tick,5);
                t5event++;
            }
            if(tracks[6].size()>t6event && tracks[6].get(t6event).getTick()<=tick){
                message = tracks[6].get(t6event).getMessage();
                printNote(message,tick,6);
                t6event++;
            }
            if(tracks[7].size()>t7event && tracks[7].get(t7event).getTick()<=tick){
                message = tracks[7].get(t7event).getMessage();
                printNote(message,tick,7);
                t7event++;
            }
        }
        
    }
    
    static int maxTick(){
        return (int)Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(
        tracks[0].get(tracks[0].size()-1).getTick(),
        tracks[1].get(tracks[1].size()-1).getTick()),
        tracks[2].get(tracks[2].size()-1).getTick()),
        tracks[3].get(tracks[3].size()-1).getTick()),
        tracks[4].get(tracks[4].size()-1).getTick()),
        tracks[5].get(tracks[5].size()-1).getTick()),
        tracks[6].get(tracks[6].size()-1).getTick()),
        tracks[7].get(tracks[7].size()-1).getTick());
    }
    
    static void printNote(MidiMessage message, int tick, int track){
        ShortMessage sm;
        int key,octave,note,command,velocity;
        String noteName;
        if (message instanceof ShortMessage) {
            sm = (ShortMessage) message;
            command = sm.getCommand();
            velocity = sm.getData2();
            if(command==144 && velocity > 0){
                key = sm.getData1();
                octave = (key / 12)-1;
                note = key % 12;
                noteName = NOTE_NAMES[note];
                System.out.println("@" + tick + " track=" + track + " note=" + noteName + octave + " key=" + key);
            }
        }        
    }
}
