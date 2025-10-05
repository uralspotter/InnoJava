package task3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyThreadsPool {

    private int poolSize;
    private List<Thread> threads;
    private boolean isStopped = true;
    private LinkedList<Runnable> tasks;

    MyThreadsPool(int poolSize){
        this.poolSize = poolSize;
        this.isStopped = false;
        this.tasks = new LinkedList<>();
        this.threads = new ArrayList<>();
        for(int i = 0; i < this.poolSize; i++){
            this.threads.add(new Thread());
        }
    }

    public void execute(Runnable task){
        if(this.isStopped){
            throw new IllegalStateException();
        }
        tasks.add(task);
    }

    public void shutdown(){
        this.isStopped = true;
        this.threads.forEach(Thread::interrupt);
    }
}
