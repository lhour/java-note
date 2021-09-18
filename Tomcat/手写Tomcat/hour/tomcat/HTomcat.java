package hour.tomcat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

import hour.servlet.HRequest;
import hour.servlet.HResponse;
import hour.servlet.HServlet;


public class HTomcat {
    private int port = 8080;
    private ServerSocket server;
    private Map<String,HServlet> servletMapping = new HashMap();

    private Properties webProperties = new Properties();

    private void init() throws Exception{
        String WEB_INF = this.getClass().getResource("/").getPath();
        FileInputStream fis = new FileInputStream("D://文件/java-learn-note/Tomcat/手写Tomcat/hour/web.properties");
        
        webProperties.load(fis);
        
        for(Object k : webProperties.keySet()) {

            String key = k.toString();
            if(key.endsWith(".url")){
                String servletName = key.replaceAll("\\.url", "");
                String url = webProperties.getProperty(key);
                String className =webProperties.getProperty(servletName + ".className");

                HServlet obj = (HServlet)Class.forName(className).getDeclaredConstructor().newInstance();
                servletMapping.put(url, obj);
            }
        }
    }

    public void start() {

        try {
            init();

            server = new ServerSocket(this.port);

            System.out.println("HTomcat启动，端口：" + this.port);

            while(true){
                Socket client = server.accept();
                process(client);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    private void process(Socket client) throws Exception{
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        HRequest request = new HRequest(is);
        HResponse response = new HResponse(os);

        String url = request.getUrl();

        if(servletMapping.containsKey(url)){
            servletMapping.get(url).service(request, response);
        }else{
            response.write("404 - Not Found");
        }

        os.flush();
        os.close();

        is.close();
        client.close();
    }

    

}
