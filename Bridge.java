
/**
 * Bridge class for maangeing communication between UDP Server and UDP Client
 *
 * @author Upal Roy
 * @version 1.0.0
 */

import java.io.*;
import java.net.*;

public class Bridge
{
     public static void main(String args[]) throws Exception
      {
         System.out.println("Author: Upal Roy"); 
         
         DatagramSocket clientBridgeSocket = new DatagramSocket(4000);
         DatagramSocket serverBridgeSocket = new DatagramSocket();
         
         
         while(true)
         {
             byte[] receiveDataFromClient = new byte[1024];
             byte[] sendDataToServer = new byte[1024];
             byte[] receiveDataFromServer = new byte[1024];
             byte[] sendDataToClient = new byte[1024];
             
             //Recive packet from client
             DatagramPacket receivePacketFromClient = new DatagramPacket(receiveDataFromClient, receiveDataFromClient.length);
             clientBridgeSocket.receive(receivePacketFromClient);
             //Send packet to Server
             String sentence = new String( receivePacketFromClient.getData());
             sendDataToServer = sentence.getBytes();
             InetAddress IPAddress = InetAddress.getByName("localhost");
             DatagramPacket sendPacketToServer = new DatagramPacket(sendDataToServer, sendDataToServer.length, IPAddress, 5000);
             serverBridgeSocket.send(sendPacketToServer);
          
             //Receive packet from server
             DatagramPacket receivePacketFromServer = new DatagramPacket(receiveDataFromServer, receiveDataFromServer.length);
             serverBridgeSocket.receive(receivePacketFromServer);
             //Send packet to client
             String modifiedSentence = new String(receivePacketFromServer.getData());
             sendDataToClient = modifiedSentence.getBytes();          
             InetAddress IPAddressOfClient = receivePacketFromClient.getAddress();
             int portOfClient = receivePacketFromClient.getPort();
             DatagramPacket sendPacketToClient =
             new DatagramPacket(sendDataToClient, sendDataToClient.length, IPAddressOfClient, portOfClient);
             clientBridgeSocket.send(sendPacketToClient);
         }
     }
}
