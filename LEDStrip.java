import java.util.Vector;
import java.util.Arrays;

/**
 * Write a description of class LEDStrip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LEDStrip
{
    // instance variables - replace the example below with your own
    public static final Boolean[] LEDs = new Boolean[88];

    /**
     * Constructor for objects of class LEDStrip
     */
    public LEDStrip(){
        
    }
    
    public void setLights(Vector<Integer> lights){
        Arrays.fill(LEDs, Boolean.FALSE);
        for(int i=0; i<lights.size(); i++){
            LEDs[lights.get(i)-21]=true;
        }
        
        updateLEDs();
        printToConsole();
    }
    
    void updateLEDs(){
        // interface with ledstrip via Raspberry Pi IO pins
    }
    
    void printToConsole(){
        for(int i=0; i<39; i++){
            //print 39 spaces, and then middle C is the 40th key
            System.out.print(" ");
        }
        System.out.println("M");
       
        
        System.out.print("A#B");
        for(int i=0; i<=6; i++){
            System.out.print("C#D#EF#G#A#B");
        }
        System.out.println("C");
        
        System.out.print("|'|");
        for(int i=0; i<=6; i++){
            System.out.print("|'|'||'|'|'|");
        }
        System.out.println("|");
        
        for(int i=0; i<LEDs.length; i++){
            if(LEDs[i]){
                System.out.print("0");
            }else{
                System.out.print(".");
            }
        }
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
