package ex03;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// Classe pour le client
public class Client {
    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        int port = 12345;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a wordToSearch: ");
        String wordToSearch = sc.nextLine();
        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // Envoyer la requête de recherche de mot au serveur
            writer.println(wordToSearch);
            String response = "";
            while (true) {
                // Recevoir la réponse du serveur
                InputStream input = socket.getInputStream();
                if (input != null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    response = reader + reader.readLine();
                    System.out.println("Response from server: " + response);
                } else {
                    break;
                }
            }


        }
    }
}

