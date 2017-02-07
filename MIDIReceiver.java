import javax.sound.midi.Receiver;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiMessage;

/**
 * Write a description of class MIDIReceiver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MIDIReceiver implements Receiver
{
    // instance variables - replace the example below with your own
    MidiDevice inputDevice;
    Receiver receiver;
    MidiDevice.Info[] infos;

    /**
     * Constructor for objects of class MIDIReceiver
     */
    public MIDIReceiver() throws Exception
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
        
        startReceiving();
    }
    
    void startReceiving() throws Exception{
        System.out.println("Opening " + inputDevice.getDeviceInfo() );
        inputDevice.open();
        System.out.println(inputDevice.getDeviceInfo() + " was opened");
    }
    
    public void send(MidiMessage message, long timestamp){
        System.out.println("message received, timestamp" + timestamp);
    }
    
    public void close(){
        
    }
}
