package desige24.Adapter.myadp;

public class Demo {
    public static void main(String[] args) throws Exception {
        CF cf = new CF();
        Mywindows pc = new Mywindows();
        pc.runGame(cf);

       

        PCadapt adapt = new PCadapt();
        pc.runGame(adapt);
        Glory glory = new Glory();
        adapt.addPhoneGame(glory);

         /**
         * 
         * 程序报错
         * 可以再完善一下，
         * 使得此处不报错，
         * 而方法内部报错
         */
        // pc.runGame(Glory);

        pc.runGame(adapt);
    }
}
