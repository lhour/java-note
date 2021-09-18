package hour.servlet;

public abstract class HServlet {

    public void service(HRequest request, HResponse response) throws Exception{
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else{
            doPost(request, response);
        }
    }

    public abstract void doGet(HRequest request, HResponse response) throws Exception;

    public abstract void doPost(HRequest request, HResponse response) throws Exception;



}