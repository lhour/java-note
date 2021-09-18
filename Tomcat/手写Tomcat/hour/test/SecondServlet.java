package hour.test;

import hour.servlet.HRequest;
import hour.servlet.HResponse;
import hour.servlet.HServlet;

public class SecondServlet extends HServlet{

    @Override
    public void doGet(HRequest request, HResponse response) throws Exception {
        // TODO Auto-generated method stub
        response.write("Second");
    }

    @Override
    public void doPost(HRequest request, HResponse response) throws Exception {
        // TODO Auto-generated method stub
        response.write("Second");
    }
    
}
