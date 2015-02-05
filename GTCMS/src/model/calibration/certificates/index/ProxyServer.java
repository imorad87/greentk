package model.calibration.certificates.index;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer {

	public static void main(String[] args) {

		ServerSocket cDaemonSocket = null;
		try {
			cDaemonSocket = new ServerSocket(8080);
			while (true) {
				try {
					Socket ClientSocket = cDaemonSocket.accept();
					(new ClientHandler(ClientSocket)).start();
				} catch (Exception e) {
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				cDaemonSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static class ClientHandler extends Thread {
		private Socket socket = null;

		ClientHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {

			try {
				System.out.println("Accepted connection");
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while (in.read() > 0) {
					System.out.println(in.readLine());
				}

				

				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				out.write("<html><body> <h1> proxy </h1> </body> </html>");
				out.flush();
			} catch (Exception e) {

			}

		}
	}

}
