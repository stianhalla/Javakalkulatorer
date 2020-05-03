package annet.oppgaver;

import kalkulatorer.macro.Helpers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerTest {
    public static ArrayList<StudentAdresse> studnetAdreser = getAdresser();

    public static void main(String[] args) throws IOException {

        System.out.println(Helpers.rundAv(10.2011, 2));
        System.out.println(Helpers.erGyldigOrd("asdÅlsakd"));
        System.out.println(Helpers.storForbokstav("fasfhodsahf asdf"));
        System.out.println(Helpers.rundAvTilHeltall(10.4));



        /*
        ServerSocket serverSocket = new ServerSocket(8000);

        Socket socket = serverSocket.accept();
        System.out.println("klient koblett til");

        ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
            int status = fromClient.readInt();
            System.out.println(status);

            if(status == 1 ) {
                try {
                    StudentAdresse studentAdresse = (StudentAdresse) fromClient.readObject();
                    System.out.println(studentAdresse);
                    studnetAdreser.add(studentAdresse);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                toClient.writeObject(studnetAdreser.get(1));
                toClient.flush();
            }
        }
        */
    }

    public static ArrayList<StudentAdresse> getAdresser() {
        ArrayList<StudentAdresse> liste = new ArrayList<>();
        try {
            liste.add(new StudentAdresse("per gjørund", "harra 55", "Hokksund", "buskerud", "3300"));
            liste.add(new StudentAdresse("nils kajander", "balle 5", "Porskgrun", "Annet", "1256"));
            liste.add(new StudentAdresse("pernil gjørund", "sss 55", "Norske", "Drammen", "3800"));
            liste.add(new StudentAdresse("tude kajude", "simpel 33", "Alta", "Balta", "9999"));
        } catch (UloveligPostnummerUnntak uloveligPostnummerUnntak) {
            uloveligPostnummerUnntak.printStackTrace();
        }
        return liste;
    }
}
