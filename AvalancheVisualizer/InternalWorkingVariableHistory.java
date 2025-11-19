package avalanche;

public final class InternalWorkingVariableHistory {
    private long[][] longHistory;
    private String result;
    private int cursor=0;


    public InternalWorkingVariableHistory(String result, int historyCount){
        this.result=result;
        this.longHistory=new long[historyCount][];
    }

    long[][] history(){
        return this.longHistory;
    }
    // String longHistory(){
    //     return Arrays.deepToString(longHistory)+"\n"+this.result+"\n\n";
    // }
    String result(){return this.result;}
    
    boolean addData(long[] arr){
        if (cursor==longHistory.length)return false;

        this.longHistory[cursor++]=arr;
        return true;
    }
    boolean setResult(String result){
        try{
            if (result==null)return false;
            if (this.result==null){
                this.result=result;
                return true;
            }
            return false;
        }
        catch(Exception e){return false;}
    }

    public String toString(){return this.result;}
}
