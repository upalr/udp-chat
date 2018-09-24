//This code is implemented from this URL (https://systembash.com/a-simple-java-udp-server-and-udp-client/)

/**
 * UDPServer class for maangeing UDP Server
 *
 * @author Upal Roy
 * @version 1.0.0
 */

import java.io.*;
import java.net.*;

class UDPServer
{
    public static void main(String args[]) throws Exception
      {
         System.out.println("Author: Upal Roy");
         
         DatagramSocket serverSocket = new DatagramSocket(5000);           
         while(true)
         {
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
              
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
              
            saveChatHistory(sentence);
              
            System.out.println("RECEIVED: " + sentence);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket =
            new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
         }
      }
     
    /**
     * Save the messages received by the server
     *
     * @param  sentence sentence is the data which should be save in the text file
     */    
    private static void saveChatHistory(String sentence)
    {
        try
        {
            PrintWriter pw = new PrintWriter(new FileWriter("text.txt",true));
            pw.println(sentence.replace("\0", ""));
            pw.close();
        }
        catch(Exception ex)
        {
            System.err.println("Exception: " + ex.getMessage());
        }  
    }
}