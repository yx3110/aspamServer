import java.sql.*;
import java.net.*;
import java.util.*;
import java.io.*;


public class serverMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("Could not find JDBC driver"+e);
			System.exit(1);
		}
		Connection conn = null;
		try{
			System.out.println("Establishing connection with Imperial psql database");
			conn = DriverManager.getConnection("jdbc:postgresql://db.doc.ic.ac.uk/yx3110","yx3110","kyJZ7MyzRW");
			System.out.println("Connection with database made successfully");
		}catch (Exception e){
			System.out.println(e);
		}
		try{
			String curMsg;
			ServerSocket serverSocket = new ServerSocket(8080);
			System.out.println("Server socket created");
			
			Socket socket = serverSocket.accept();
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while(true){
				while((curMsg=in.readLine())!=null&& socket.isConnected()){
					System.out.println("message:"+curMsg);
				}
				if(socket.isConnected()){
					System.out.println("Socket still connected");
				}else{
					System.out.println("Socket closed");
				}
			}
		}catch(Exception e){
			
		}
	}

}
