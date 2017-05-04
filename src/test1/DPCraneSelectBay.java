package test1;

/**
 * Created by csw on 2017/3/8 19:42.
 * Explain: DP过程用于决策的作业时间量
 */
public class DPCraneSelectBay {

    private Pair pair;
    private Long dpWorkTime;
    private Double dpDistance;
    private boolean isTroughMachine;

    public DPCraneSelectBay(Pair pair) {
        this.pair = pair;
        this.dpWorkTime = 0L;
        this.dpDistance = Double.MAX_VALUE;
        this.isTroughMachine = false;
    }

    public Long getDpWorkTime() {
        return dpWorkTime;
    }

    public void setDpWorkTime(Long dpWorkTime) {
        this.dpWorkTime += dpWorkTime;
    }

    public Double getDpDistance() {
        return dpDistance;
    }

    public void setDpDistance(Double dpDistance) {
        this.dpDistance = dpDistance;
    }

    public boolean isTroughMachine() {
        return isTroughMachine;
    }

    public void setTroughMachine(boolean troughMachine) {
        isTroughMachine = troughMachine;
    }

    @Override
    public boolean equals(Object obj) {
        return this.pair.getFirst().equals(((Pair) obj).getFirst())
                && this.pair.getSecond().equals(((Pair) obj).getSecond());
    }
}
