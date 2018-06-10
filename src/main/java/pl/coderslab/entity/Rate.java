package pl.coderslab.entity;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;


@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "no",
        "effectiveDate",
        "bid",
        "ask"
})
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonProperty("no")
    private String no;

    @JsonProperty("effectiveDate")
    private Date effectiveDate;

    @JsonProperty("bid")
    private Double bid;

    @JsonProperty("ask")
    private Double ask;

    private String currencyCode;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    private LocalDateTime created;

    //Constructor

    public Rate(){}

    public Rate(String no,Date effectiveDate, Double bid, Double ask, String currencyCode){
        setNo(no);
        setEffectiveDate(effectiveDate);
        setBid(bid);
        setAsk(ask);
        setCurrencyCode(currencyCode);
    }

    //Getters and Setters

    @JsonProperty("no")
    public String getNo() {
        return no;
    }

    @JsonProperty("no")
    public void setNo(String no) {
        this.no = no;
    }

    @JsonProperty("effectiveDate")
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    @JsonProperty("effectiveDate")
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @JsonProperty("bid")
    public Double getBid() {
        return bid;
    }

    @JsonProperty("bid")
    public void setBid(Double bid) {
        this.bid = bid;
    }

    @JsonProperty("ask")
    public Double getAsk() {
        return ask;
    }

    @JsonProperty("ask")
    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDateTime getCreated() { return created; }

    public void setCreated(LocalDateTime created) { this.created = created; }


}