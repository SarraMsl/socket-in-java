package ex03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static String result = "";

    public static void main(String[] args) throws IOException {
        int port = 12345;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a paragraph: ");
        String paragraph;
        paragraph = sc.nextLine();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Recevoir la requête de recherche de mot du client
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String wordToSearch = reader.readLine();

                // Effectuer la recherche de mot dans le paragraphe
                result = "Word not found";
                if (paragraph.contains(wordToSearch)) {
                  //  result = "Word found";
                    int count = 0;
                    for (int i = -1; (i = paragraph.indexOf(wordToSearch, i + 1)) != -1; i++) {
                        count++;
                        System.out.println("The word '" + wordToSearch + "' appeared at position " + i + " .");
                        OutputStream output = socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(output, true);
                        writer.println("The word '" + wordToSearch + "' appeared at position " + i + " .");
                    }

                    System.out.println("The word '" + wordToSearch + "' appeared " + count + " times.");
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println("The word '" + wordToSearch + "' appeared " + count + " times.");

                }else{
                    result = "Word not found";
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println(result);
                }

            }
        }
    }
}
