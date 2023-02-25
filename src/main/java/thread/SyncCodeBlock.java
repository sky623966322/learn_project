package thread;

public class SyncCodeBlock {
    public int i;

    public void syncTask(){
        //同步代码库
        synchronized (this){
            i++;
        }
    }

    public synchronized void syncTask2(){
        i++;
    }

    public synchronized static void syncTask3(){

    }
}
