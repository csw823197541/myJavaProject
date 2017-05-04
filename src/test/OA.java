package test;

/**
 * Created by csw on 2017/3/7 16:25.
 * Explain:
 */
public class OA {

    private Integer id;

    private Integer to;
    private Long toWT;
    private Integer from;
    private Long fromWT;

    public OA() {

    }

    public OA(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Long getToWT() {
        return toWT;
    }

    public void setToWT(Long toWT) {
        this.toWT = toWT;
    }

    public Long getFromWT() {
        return fromWT;
    }

    public void setFromWT(Long fromWT) {
        this.fromWT = fromWT;
    }
}
