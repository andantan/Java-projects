package TermProjectPrototype.Crawler;

import TermProjectPrototype.Crawler.Coupang.CoupangBehavior;
import TermProjectPrototype.Crawler.Gmarket.GmarketBehavior;
import TermProjectPrototype.Crawler._11_st.StreetBehavior;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Pool extends Thread {
    private final static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
    private final static CoupangBehavior COUPANG_BEHAVIOR = new CoupangBehavior();
    private final static GmarketBehavior GMARKET_BEHAVIOR = new GmarketBehavior();
    private final static StreetBehavior STREET_BEHAVIOR = new StreetBehavior();
    private final static List<Behavior> BEHAVIORS = Arrays.asList(COUPANG_BEHAVIOR, GMARKET_BEHAVIOR, STREET_BEHAVIOR);
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static String[] SEARCH_SET = new String[3];
    private final static Pool POOL = new Pool();
    private final static String LINE = "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+";

    private final static Runnable COUPANG_RUNNABLE = () -> {
        Thread.currentThread().setName("[Crawler.Coupang.CoupangBehavior] executable pipe");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY - 6);

        System.out.println("ThreadPool activated → COUPANG_RUNNABLE executed INFO\n[ThreadExecutorService PoolSize]: "
                + threadPoolExecutor.getPoolSize() + "\n[ThreadName]: " + Thread.currentThread().getName()
                + "\n[ThreadPriority]: " + Thread.currentThread().getPriority() + "\n" + LINE);

        COUPANG_BEHAVIOR.call(SEARCH_SET);
    };

    private final static Runnable GMARKET_RUNNABLE = () -> {
        Thread.currentThread().setName("[Crawler.Gmarket.GmarketBehavior] executable pipe");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY - 1);

        System.out.println("ThreadPool activated → GMARKET_RUNNABLE executed INFO\n[ThreadExecutorService PoolSize]: "
                + threadPoolExecutor.getPoolSize() + "\n[ThreadName]: " + Thread.currentThread().getName()
                + "\n[ThreadPriority]: " + Thread.currentThread().getPriority() + "\n" + LINE);

        GMARKET_BEHAVIOR.call(SEARCH_SET);
    };

    private final static Runnable STREET_RUNNABLE = () -> {
        Thread.currentThread().setName("[Crawler._11_st.StreetBehavior] executable pipe");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY - 3);

        System.out.println("ThreadPool activated → STREET_RUNNABLE executed INFO\n[ThreadExecutorService PoolSize]: "
                + threadPoolExecutor.getPoolSize() + "\n[ThreadName]: " + Thread.currentThread().getName()
                + "\n[ThreadPriority]: " + Thread.currentThread().getPriority() + "\n" + LINE);

        STREET_BEHAVIOR.call(SEARCH_SET);
    };

    private Pool() { }

    public static Pool getInstance() { return POOL; }

    public void execute() {
        initQuery();

        System.out.println(LINE);

        Future<?> GMARKET_RUNNABLE_future = executorService.submit(GMARKET_RUNNABLE);
        Future<?> ST_RUNNABLE_future = executorService.submit(STREET_RUNNABLE);
        Future<?> COUPANG_RUNNABLE_future = executorService.submit(COUPANG_RUNNABLE);

        executorService.shutdown();

        while (true) {
            if (GMARKET_RUNNABLE_future.isDone() && ST_RUNNABLE_future.isDone() && COUPANG_RUNNABLE_future.isDone()) {
                for (Behavior behavior: BEHAVIORS) {
                    System.out.println(LINE + LINE + LINE);
                    behavior.print();
                }

                break;
            }
        }
    }

    protected static void initQuery() {
        System.out.print("검색어: ");
        SEARCH_SET[0] = String.join("+", SCANNER.nextLine().strip().split(" "));

        System.out.print("옵션(판매량순, 최신순, 높은 가격순, 낮은 가격순): ");
        SEARCH_SET[1] = SCANNER.nextLine().strip();

        System.out.print("검색 생품 개수: ");
        SEARCH_SET[2] = SCANNER.nextLine().strip();

        System.out.printf("검색어: %s, 옵션: %s, 개수: %d%n", SEARCH_SET[0].replace("+", " "), SEARCH_SET[1], Integer.parseInt(SEARCH_SET[2]));
    }
}
