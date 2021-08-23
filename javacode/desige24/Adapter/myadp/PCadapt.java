package desige24.Adapter.myadp;

public class PCadapt implements PCgame {

    PhoneGame phoneGame;

    public void addPhoneGame(PhoneGame phoneGame){
        this.phoneGame = phoneGame;
        System.out.println(phoneGame.getName() + "已适配完成");

    }

    @Override
    public String getName() throws Exception{
        try{
            return phoneGame.getName();
        }catch(Exception e){
            return this.toString() + " 手游适配器 未连接手机游戏";
        }
    }
    
}
