import javax.sound.midi.Receiver;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Write a description of class MIDIReceiver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MIDIReceiver extends midihub implements Receiver
{
    // instance variables - replace the example below with your own
    MidiDevice inputDevice;
    Receiver receiver;
    MidiDevice.Info[] infos;
    static long previousTick=0;
    static long receivedTimestamp=0;
    static int currentNote=0;
    static boolean receivedNote = false;
    static boolean sending = false;
    static boolean currentMessageReceived = false;
    static boolean processingPreviousMessage = false;
    static Vector<Integer> noteBatch = new Vector<Integer>();
    
    static int testVar=0;
    
    Thread t;
    Timer timer = new Timer();

    /**
     * Constructor for objects of class MIDIReceiver
     */
    public MIDIReceiver(){
        
    }
    
    public void init() throws Exception
    {
        System.out.println("Getting MidiDevice Infos");
        infos = MidiSystem.getMidiDeviceInfo();
        System.out.println(infos.length + " MidiDevices found: ");
        for(int i=0; i<infos.length; i++){
            System.out.println((i+1)+": "+MidiSystem.getMidiDevice(infos[i]).getDeviceInfo());
        }
        System.out.println("");
        inputDevice = MidiSystem.getMidiDevice(infos[1]);
        receiver = MidiSystem.getReceiver();
        System.out.println("Opening " + inputDevice.getDeviceInfo() );
        inputDevice.open();
        System.out.println(inputDevice.getDeviceInfo() + " was opened"); 
        
        final MIDIReceiver heartbeatThread = new MIDIReceiver();
        Timer timer = new Timer();
        timer.schedule
        (
            new TimerTask()
            {
                public void run(){
                    heartbeatThread.checkSendBatchToHub();
                }
            },0,200
        );        
    }
    
    public void checkSendBatchToHub(){
        //System.out.println(System.currentTimeMillis()-receivedTimestamp);
        if(sending || ((System.currentTimeMillis()-receivedTimestamp) < 10)){
            super.setNextReceivedNotes(noteBatch);
            noteBatch = new Vector<Integer>();
            sending=false;
        }
    }
    
    public void send(MidiMessage message, long currentTick){
        if (message instanceof ShortMessage) {
            currentMessageReceived=false;
            do
            {
                if(!sending){
                    ShortMessage sm = (ShortMessage) message;
                    currentNote= sm.getData1();
                
                    if(((currentTick-previousTick) > 10) && !noteBatch.isEmpty()){
                        sending=true;
                    }else{
                       noteBatch.addElement(currentNote);
                       receivedTimestamp = System.currentTimeMillis();
                       previousTick=currentTick;
                       currentMessageReceived=true;
                    }
                }
            }while(!currentMessageReceived);
        }
    }
    
    public void close(){
        
    }
}
