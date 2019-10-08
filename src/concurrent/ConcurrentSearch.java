package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentSearch {
    static int[] arr={46, 84, 456, 1, -8, 456, 9, -56, 995};
    static ExecutorService pool = Executors.newCachedThreadPool();
    static final int Thread_Nun = 2;
    static AtomicInteger result = new AtomicInteger(-1);


    public static void main(String[] args) {

        try {
           int index = pSearch(-8);
           System.out.println(index);
        }catch (InterruptedException e){

        }catch (ExecutionException e){

        }
    }

    public static int Search(int begin, int end, int searchValue) {
        for (int i = begin; i < arr.length; i++) {
            if (result.get() >= 0) {
                return result.get();
            }
            if (arr[i] == searchValue) {
                if (!result.compareAndSet(-1, i)) {
                    return result.get();
                }
            }
        }
        return -1;
    }

    public static int pSearch(int serchVlue) throws InterruptedException, ExecutionException {
        int subArraySize = arr.length / Thread_Nun + 1;
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i += subArraySize) {
            int end = i + subArraySize;
            if (end > arr.length) end = arr.length;
            list.add(pool.submit(new SearchTask(i, end, serchVlue)));
            for (Future<Integer> fu : list) {
                if ( fu.get()>= 0) {
                    return fu.get();
                }
            }
        }
        return -1;
    }

}

class SearchTask implements Callable<Integer> {
    int begin, end, searchVlaue;

    public SearchTask(int begin, int end, int searchVlaue) {
        this.begin = begin;
        this.end = end;
        this.searchVlaue = searchVlaue;
    }

    public Integer call() {
        int re = ConcurrentSearch.Search(begin, end, searchVlaue);
        return re;
    }
}
