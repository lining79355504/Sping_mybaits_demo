package com.demo.concurrent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author mort
 * @Description
 * @date 2022/4/22
 **/
public class BizTest {

    public void futureTest(){

        //future test
        FutureTask<List<Integer>> futureTask = new FutureTask<>(new Callable<List<Integer>>() {
            @Override
            public List<Integer> call() throws Exception {
                return Collections.EMPTY_LIST;
            }
        });
        new Thread(futureTask).start();

        try {
            List<Integer> datas = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        // runnbale test
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
