package avalanche;
import java.text.DecimalFormat;
sealed public class InternalAvalanche permits ExternalAvalanche{
    static String format = "00.00 %";
    static DecimalFormat formatter = new DecimalFormat(format);
    public static String getAnsiRgbBackgroundCode(int mode, int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) return "";
        
        return String.format("\033[%d;2;%d;%d;%dm", mode, r , g, b);
    }
    public final static String zeroCode = "\033[0m";
    public static String percentageColor(double percent){
        // if(percent<25)return getAnsiRgbBackgroundCode(255, 0, 0);
        // if(percent<50)return getAnsiRgbBackgroundCode(255, 165, 0);
        // return getAnsiRgbBackgroundCode(0, 255, 0);
        double inc = 1;
        // return getAnsiRgbBackgroundCode(38, (int)Math.min((100.0-percent)*2.55*inc, 255), (int)Math.min(percent*2.55*inc, 255), 0);
        double distanceFromIdeal = Math.abs(percent - 50);
        int 
        red   = (int)Math.min(distanceFromIdeal * 5.1 * inc, 255),
        green = (int)Math.min((50 - distanceFromIdeal) * 5.1 * inc, 255),
        blue  = 0;

        return getAnsiRgbBackgroundCode(38, red, green, blue);
    }
    private static String bar(double percent, int length) {
        return percentageColor(percent)+"█".repeat((int)(percent*length/100))+("░".repeat(length-(int)(percent*length/100)))+" - "+formatter.format(percent*0.01)+zeroCode;
    }
    private static double compareTwoHistoriesFullHex(long[][] historyData, int h1, int h2){   
        long[] his1 = historyData[h1];
        long[] his2 = historyData[h2];
        // System.out.println(Helper.toHexaDec(his1[0], 16));
        // System.out.println(Helper.toHexaDec(his2[0], 16));
        // for (int )
        // System.out.println(Helper.percentageDifference(his1[0], his2[0])+" %");
        double currentRound = 0.0;
        double[] diffs = new double[his1.length];
        for (int i=0; i<his1.length; i++){
            diffs[i] = Helper.hammingDistanceInPercent(his1[i], his2[i]);
            currentRound+=diffs[i];

        }
        // System.out.print("Round "+String.format("%2d", h1)+": "+((h1==0?" 0.0000":(currentRound/his1.length)))+" % ");
        System.out.print("Round "+String.format("%2d", h1)+": "+percentageColor(h1!=0?currentRound/his1.length:0)+formatter.format((h1==0?0:(currentRound*0.01/his1.length)))+zeroCode+"   | ");
        for (int i=0; i<his1.length; i++){
            System.out.print(percentageColor(diffs[i])+Helper.toHexaDec(his1[i], 16)+zeroCode+" ");
        }
        // System.out.println("");
        System.out.print("\nRound "+String.format("%2d", h2)+": "+percentageColor(currentRound/his1.length)+formatter.format(currentRound*0.01/his1.length)+zeroCode+"   | ");
        for (int i=0; i<his2.length; i++){
            
            System.out.print(percentageColor(diffs[i])+Helper.toHexaDec(his2[i], 16)+zeroCode+" ");
            
        }
        return currentRound/his1.length;
        // System.out.println("");
    }
    private static double compareTwoHistoriesBarGraph(long[][] historyData, int h1, int h2, int length){
        double avalanche = 0.0;
        long his1[] = historyData[h1];
        long his2[] = historyData[h2];
        System.out.print("Rounds "+(String.format("%2d", h1+1))+" - "+(String.format("%2d", h2+1))+": ");
        for (int i=0; i<his1.length; i++){
            avalanche+=Helper.hammingDistanceInPercent(his1[i], his2[i]);
        }
        avalanche/=his1.length;
        // System.out.println(percentageColor(avalanche)+"█".repeat((int)(avalanche*length/100))+("░".repeat(length-(int)(avalanche*length/100)))+" - "+formatter.format(avalanche*0.01)+zeroCode);
        System.out.println(bar(avalanche, length));
        return avalanche;
    }
    private static double compareTwoHistories(long[][] historyData, int h1, int h2, boolean shallPrint){
        double avalanche = 0.0;
        long his1[] = historyData[h1];
        long his2[] = historyData[h2];
        if(shallPrint)System.out.print("Rounds "+(String.format("%2d", h1+1))+" - "+(String.format("%2d", h2+1))+": ");
        for (int i=0; i<his1.length; i++){
            avalanche+=Helper.hammingDistanceInPercent(his1[i], his2[i]);
        }
        avalanche/=his1.length;
        if(shallPrint)System.out.println(percentageColor(avalanche)+" "+formatter.format(avalanche*0.01)+zeroCode);
        return avalanche;
    }
    private static double compareTwoHistoriesPerVariableDiffusion(long[][] historyData, int h1, int h2, String[] names){
        double avalanche = 0.0;
        long his1[] = historyData[h1];
        long his2[] = historyData[h2];
        System.out.print("Rounds "+(String.format("%2d", h1+1))+" - "+(String.format("%2d", h2+1))+": ");
        for (int i=0; i<his1.length; i++){
            double cur = Helper.hammingDistanceInPercent(his1[i], his2[i]);
            System.out.print(percentageColor(cur)+String.format("%15s", names[i]+"="+formatter.format(cur*0.01))+zeroCode);
            avalanche+=cur;
        }
        System.out.println(percentageColor(avalanche/his1.length)+"      - Total: "+formatter.format(avalanche*0.01/his1.length)+zeroCode);
        avalanche/=his1.length;
        // System.out.println(percentageColor(avalanche)+"█".repeat((int)(avalanche*length/100))+("░".repeat(length-(int)(avalanche*length/100)))+" - "+formatter.format(avalanche*0.01)+zeroCode);
        return avalanche;
    }
    private static double showCumultativeAvalancheTotal(long[][] historyData, int h1, boolean shallPrint){

        if (h1==0){
            if(shallPrint)System.out.println("Round  1 : "+percentageColor(0)+formatter.format(0)+zeroCode);
            return 0;
        }
        double avalanche = 0;
        long[] his1 = historyData[0];
        long[] his2 = historyData[h1];
        if(shallPrint)System.out.print("Round "+String.format("%2d", h1+1)+" : ");
        for (int i=0; i<his1.length; i++){
            double cur = Helper.hammingDistanceInPercent(his1[i], his2[i]);
            // System.out.print(String.format("%10s", formatter.format(cur)));
            avalanche+=cur;
        }
        if(shallPrint)System.out.println(percentageColor(avalanche/his1.length)+formatter.format(avalanche*0.01/his1.length)+zeroCode);

        return avalanche/his1.length;
    }
    private static double showCumultativeAvalancheTotal(long[][] historyData, int h1, int length){

        if (h1==0){
            System.out.println("Round  1 : "+percentageColor(0)+"░".repeat(length)+" - "+formatter.format(0)+zeroCode);
            return 0;
        }
        double avalanche = 0;
        long[] his1 = historyData[0];
        long[] his2 = historyData[h1];
        System.out.print("Round "+String.format("%2d", h1+1)+" : ");
        for (int i=0; i<his1.length; i++){
            double cur = Helper.hammingDistanceInPercent(his1[i], his2[i]);
            // System.out.print(String.format("%10s", formatter.format(cur)));
            avalanche+=cur;
        }
        // System.out.println(percentageColor(avalanche/his1.length)+("█".repeat((int)(avalanche*length/(100*his1.length)))+("░".repeat(length-(int)(avalanche*length/(100*his1.length))))+" - ")+formatter.format(avalanche*0.01/his1.length)+zeroCode);
        System.out.println(bar(avalanche/his1.length, length));

        return avalanche/his1.length;
    }







    public static void hexHistory(long[][] history){
        double total = 0;
        for (int i=0; i<history.length-1; i++){
            System.out.print("\r");
            total+=compareTwoHistoriesFullHex(history, i, i+1);
        }
        
        System.out.println("\nTotal Avalanche : "+percentageColor(total/(history.length-1))+String.format("%6.4f %%", (total/(history.length-1)))+zeroCode);
    }
    public static void avgAvalanche(long[][] history){
        double total = 0;
        for (int i=0; i<history.length-1; i++){
            total+=compareTwoHistories(history, i, i+1, true);
        }
        System.out.println("Average Avalanche: "+percentageColor(total/(history.length-1))+String.format("%6.4f %%", (total/(history.length-1)))+zeroCode);
    }
    public static void avgAvalancheHeatMap(long[][] history, int l){
        double total = 0;
        for (int i=0; i<history.length-1; i++){
            total+=compareTwoHistoriesBarGraph(history, i, i+1, l);
        }
        System.out.println("Average Avalanche: "+percentageColor(total/(history.length-1))+String.format("%6.4f %%", (total/(history.length-1)))+zeroCode);
    }
    public static void avgAvalancheTabular(long[][] history, int dataPerRow){
        if (dataPerRow==0){return;}
        int traced=0;
        double total=0;
        while(traced<history.length-1){
            double[] data = new double[dataPerRow];
            System.out.print("Round : ");
            inn: for (int i=0; i<dataPerRow; i++){
                if (traced+i>=history.length-1)break inn;
                System.out.print(String.format("%8s",
                String.format("%2d", traced+i+1)+" - "+String.format("%2d", traced+i+2)
                )+" | ");
                data[i]=compareTwoHistories(history, traced+i, traced+i+1, false);
                total+=data[i];
                // System.out.println(" "+dataPerRow+" "+traced%dataPerRow);
            }
            
            System.out.print("\nChange: ");
            inn: for (int i=0; i<dataPerRow; i++){
                if (traced>=history.length-1)break inn;
                System.out.print(
                    percentageColor(data[i])+
                    String.format(
                        "%8s",
                        formatter.format(data[i]*0.01)
                    )+zeroCode+" | "
                );
                traced++;
            }
            System.out.println("\n");
        }
        System.out.println("Average Avalanche: "+percentageColor(total/(history.length-1))+String.format("%6.4f %%", (total/(history.length-1)))+zeroCode);
    }
    public static void perVariableDiffusion(long[][] history, String[] names){
        double total = 0;
        for (int i=0; i<history.length-1; i++){
            total+=compareTwoHistoriesPerVariableDiffusion(history, i, i+1, names);
        }
        System.out.println("Average Avalanche: "+percentageColor(total/(history.length-1))+String.format("%6.4f %%", (total/(history.length-1)))+zeroCode);
    }
    public static void cumultativeAvalanche(long[][] history){
        double total=0;
        for (int i=0; i<64; i++){
            total=showCumultativeAvalancheTotal(history, i, true);
        }

        System.out.println("Final Cumultative Avalanche: "+percentageColor(total)+String.format("%6.4f %%", total)+zeroCode);
    }
    public static void cumultativeAvalancheHeatMap(long[][] history, int l){
        double total=0;
        for (int i=0; i<64; i++){
            total=showCumultativeAvalancheTotal(history, i, l);
        }

        System.out.println("Final Cumultative Avalanche: "+percentageColor(total)+String.format("%6.4f %%", total)+zeroCode);
    }
    public static void cumultativeAvalancheTabular(long[][] history, int dataPerRow){
        if (dataPerRow==0){return;}
        System.out.println("Avalanche Buildup: ");
        int traced=0;
        double total=0;
        while(traced<history.length){
            double[] data = new double[dataPerRow];
            System.out.print("Round : ");
            inn: for (int i=0; i<dataPerRow; i++){
                if (traced+i>=history.length)break inn;
                System.out.print(String.format("%"+(format.length()+2)+"d",traced+i+1)+" | ");
                total=showCumultativeAvalancheTotal(history, traced+i, false);
                data[i]=total;
                // System.out.println(" "+dataPerRow+" "+traced%dataPerRow);
            }
            
            System.out.print("\nChange: ");
            inn: for (int i=0; i<dataPerRow; i++){
                if (traced>=history.length)break inn;
                System.out.print(
                    percentageColor(data[i])+
                    String.format(
                        "%"+(format.length()+2)+"s",
                        formatter.format(data[i]*0.01)
                    )+zeroCode+" | "
                );
                traced++;
            }
            System.out.println("\n");
        }
        System.out.println("Final Cumultative Avalanche: "+percentageColor(total)+String.format("%6.4f %%", total)+zeroCode);
    }


}
