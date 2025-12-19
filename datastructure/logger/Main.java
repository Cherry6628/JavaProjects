package datastructure.logger;

import datastructure.AVLTree;
import java.util.*;

enum LogLevel {
    DEBUG, INFO, WARN, ERROR, CRITICAL
}

class Log implements Comparable<Log> {
    final long timestamp;
    final LogLevel level;
    final String message;

    public Log(long timestamp, LogLevel level, String message) {
        this.timestamp = timestamp;
        this.level = Objects.requireNonNull(level);
        this.message = Objects.requireNonNull(message);
    }

    @Override
    public int compareTo(Log o) {
        int c = Long.compare(this.timestamp, o.timestamp);
        if (c != 0) return c;
        c = this.level.compareTo(o.level);
        if (c != 0) return c;
        return this.message.compareTo(o.message);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Log)) return false;
        Log l = (Log) o;
        return timestamp == l.timestamp && level == l.level && message.equals(l.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, level, message);
    }

    @Override
    public String toString() {
        return String.format("[%s] %-40s (%d)", level, message, timestamp);
    }
}

class LogSystem {

    private final AVLTree<Log> tree = new AVLTree<>();

    public void addLog(long ts, LogLevel lvl, String msg) {
        tree.insert(new Log(ts, lvl, msg));
    }

    private boolean match(Log l, String keyword, LogLevel level) {
        if (keyword != null && !keyword.isEmpty() && !l.message.contains(keyword)) return false;
        if (level != null && l.level != level) return false;
        return true;
    }

    public List<Log> searchAfter(long ts, String keyword, LogLevel level) {
        List<Log> base = tree.rangeSearchMin(new Log(ts, LogLevel.DEBUG, ""));
        List<Log> out = new ArrayList<>();
        for (Log l : base) if (l.timestamp >= ts && match(l, keyword, level)) out.add(l);
        return out;
    }

    public List<Log> searchBefore(long ts, String keyword, LogLevel level) {
        List<Log> base = tree.rangeSearchMax(new Log(ts, LogLevel.CRITICAL, "~"));
        List<Log> out = new ArrayList<>();
        for (Log l : base) if (l.timestamp <= ts && match(l, keyword, level)) out.add(l);
        return out;
    }

    public List<Log> searchAt(long ts, String keyword, LogLevel level) {
        List<Log> all = tree.inorder();
        List<Log> out = new ArrayList<>();
        for (Log l : all)
            if (l.timestamp == ts && match(l, keyword, level)) out.add(l);
        return out;
    }

    public List<Log> searchAll(String keyword, LogLevel level) {
        List<Log> all = tree.inorder();
        List<Log> out = new ArrayList<>();
        for (Log l : all) if (match(l, keyword, level)) out.add(l);
        return out;
    }

    public void deleteLogs(List<Log> logs) {
        for (Log l : logs) tree.delete(l);
    }

    public List<Log> allLogs() {
        return tree.inorder();
    }

    public void clear() {
        tree.clear();
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    static LogLevel readLevel() {
        System.out.println("Level (DEBUG/INFO/WARN/ERROR/CRITICAL or blank):");
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return null;
        return LogLevel.valueOf(s);
    }

    static long readTimestamp() {
        System.out.println("Timestamp (blank/invalid = 0):");
        try {
            long v = Long.parseLong(sc.nextLine());
            return v < 0 ? 0 : v;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        LogSystem ls = new LogSystem();

        while (true) {
            System.out.println("""
1) Add log
2) Search log
3) Get all logs
4) Delete all logs
5) Delete particular log
6) Exit
""");
            String c = sc.nextLine();

            switch (c) {
                case "1" -> {
                    System.out.print("Message: ");
                    String msg = sc.nextLine();
                    LogLevel lvl = readLevel();
                    if (lvl == null) lvl = LogLevel.DEBUG;
                    ls.addLog(System.currentTimeMillis(), lvl, msg);
                }

                case "2" -> {
                    System.out.println("a) after  b) before  c) at  d) keyword/level");
                    String o = sc.nextLine();
                    System.out.print("Keyword (blank = all): ");
                    String kw = sc.nextLine();
                    LogLevel lvl = readLevel();

                    List<Log> res;
                    if (o.equals("a")) res = ls.searchAfter(readTimestamp(), kw, lvl);
                    else if (o.equals("b")) res = ls.searchBefore(readTimestamp(), kw, lvl);
                    else if (o.equals("c")) res = ls.searchAt(readTimestamp(), kw, lvl);
                    else res = ls.searchAll(kw, lvl);

                    res.forEach(System.out::println);
                }

                case "3" -> ls.allLogs().forEach(System.out::println);

                case "4" -> ls.clear();

                case "5" -> {
                    List<Log> all = ls.allLogs();
                    for (int i = 0; i < all.size(); i++)
                        System.out.println((i + 1) + ") " + all.get(i));
                    System.out.print("Select index: ");
                    int idx = Integer.parseInt(sc.nextLine()) - 1;
                    if (idx >= 0 && idx < all.size())
                        ls.deleteLogs(List.of(all.get(idx)));
                }

                case "6" -> { return; }
            }
        }
    }
}
