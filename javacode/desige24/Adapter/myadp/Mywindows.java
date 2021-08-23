package desige24.Adapter.myadp;

public class Mywindows implements abstractWindows{

    @Override
    public void runGame(PCgame pcgame) throws Exception {
        System.out.println(this.toString() + " 运行 ： " + pcgame.getName() );
        
    }
    
}
