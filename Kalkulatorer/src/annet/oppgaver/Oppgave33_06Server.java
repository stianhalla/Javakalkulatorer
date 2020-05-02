/*
 * Oppgave 33_06
 */
package annet.oppgaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author drizz
 * Oppgave fra nettkapittel til boka 
 */
public class Oppgave33_06Server {
    private static final String FIL_STI = "src/annet/oppgaver/student_addresser.dat"; 
    private static final File fil = new File(FIL_STI); 
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{

        ObjectInputStream inn = new ObjectInputStream(new FileInputStream(fil));
        
        Object lestObjekt = null;
        while((lestObjekt = inn.readObject()) != null){
            if(lestObjekt instanceof StudentAdresse)
                System.out.println((StudentAdresse)lestObjekt);
        }
        
        inn.close();
    }
}
