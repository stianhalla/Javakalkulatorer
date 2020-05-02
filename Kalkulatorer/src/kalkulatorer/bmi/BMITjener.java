/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorer.bmi;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author mussal
 */
public class BMITjener extends Thread {
    private static int port = 8011;
    
    private Socket socket;
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public BMITjener(Socket socket){
        this.socket = socket;
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            Thread thread = new BMITjener(socket);
            thread.start();
        }
    }
        
        
    @Override
    public void run(){
    try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            
            PersonIdx person = (PersonIdx)(in.readObject());
            

            out.writeObject(calculateBMI(person));
            
            out.close();
            socket.close();
        } catch (IOException ex) { 
            System.out.println("IO");
        } catch (ClassNotFoundException ex) { 
            System.out.println("Class");
        }
    }
    
    
    private BMIResultat calculateBMI(PersonIdx person){   

        double hoyde = person.getHoyde();
        double vekt = person.getVekt();
        int alder = person.getAlder();
        double kmi = calculateKMI(vekt, hoyde);
        kmi = Math.round(kmi*100.0)/100.0;
        
        String melding = "";
            if (kmi == 0)
                melding ="Undervektig";
            else if (kmi <= 18.5)
                melding = "Undervektig";
            else if (kmi <= 24.9)
                melding = "Normal kroppsvekt";
            else if (kmi <= 29.9)
                melding = "Overvektig";
            else if (kmi <= 34.9)
                melding = "Fedme";
            else if (kmi <= 39.9)
                melding = "Fedme, klasse II";
            else
                melding = "Fedme, klasse III (ekstrem fedme)";
        
        return new BMIResultat(kmi,melding);
            
        }
        
        

        private double calculateKMI(double vekt, double hoyde){
            return vekt / (hoyde*hoyde); 
        }
            
}
