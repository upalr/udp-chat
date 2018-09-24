//This code is implemented from this URL (https://systembash.com/a-simple-java-udp-server-and-udp-client/)

/**
 * UDPClient class for maangeing UDP Client
 *
 * @author Upal Roy
 * @version 1.0.0
 */
import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
      System.out.println("Author: Upal Roy"); 
       
      BufferedReader inFromUser =
         new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      while(true)
      {
          byte[] sendData = new byte[1024];
          byte[] receiveData = new byte[1024];
          String sentence = inFromUser.readLine();
          if(sentence.compareTo("\\q")== 0){
              break;
          }
          sendData = sentence.getBytes();
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 4000);
          clientSocket.send(sendPacket);
          
          DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
          clientSocket.receive(receivePacket);
          String modifiedSentence = new String(receivePacket.getData());
          System.out.println("FROM SERVER:" + modifiedSentence);
       }
      clientSocket.close();
   }
}