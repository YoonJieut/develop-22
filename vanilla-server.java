import com.sum.net.httpserver.HttpServer;
import com.sum.net.httpserver.HttpHandler;
import com.sum.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleJavaServer {
  private int port;
  public SimpleJavaServer(int Port) {
    this.port = port;
  }
  public void start() throws  IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new RootHandler());
    server.setExecutor(null); // default excutor
    server.start();
    System.out.printIn("server"+ port);
  }

  static class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchage) throws IOException {
      String response = "hello";
      exchage.sendResponseHeaders(200, response.length());
      OutputStream os = exchage.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  }

  public static void main(String[] args) throws IOException {
    SimpleJavaServer simpleJavaServer = new SimpleJavaServer(8000);
    simpleJavaServer.start();
  }
}