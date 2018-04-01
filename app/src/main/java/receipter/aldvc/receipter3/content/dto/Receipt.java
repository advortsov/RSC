package receipter.aldvc.receipter3.content.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Receipt extends RealmObject {

    @SerializedName("retailPlaceAddress")
    @Expose
    private String retailPlaceAddress;
    @SerializedName("kktRegId")
    @Expose
    private String kktRegId;
    @SerializedName("items")
    @Expose
    private RealmList<Item> items;

    @SerializedName("operationType")
    @Expose
    private Integer operationType;
    @SerializedName("rawData")
    @Expose
    private String rawData;

    @SerializedName("dateTime")
    @Expose
    private Date dateTime;

    @SerializedName("nds18")
    @Expose
    private Integer nds18;

    @SerializedName("ecashTotalSum")
    @Expose
    private Integer ecashTotalSum;
    @SerializedName("receiptCode")
    @Expose
    private Integer receiptCode;

    @SerializedName("fiscalDriveNumber")
    @Expose
    private String fiscalDriveNumber;

    @SerializedName("requestNumber")
    @Expose
    private Integer requestNumber;
    @SerializedName("cashTotalSum")
    @Expose
    private Integer cashTotalSum;
    @SerializedName("nds10")
    @Expose
    private Integer nds10;

    @PrimaryKey
    @SerializedName("fiscalSign")
    @Expose
    private String fiscalSign;

    @SerializedName("fiscalDocumentNumber")
    @Expose
    private Integer fiscalDocumentNumber;

    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("shiftNumber")
    @Expose
    private Integer shiftNumber;
    @SerializedName("senderAddress")
    @Expose
    private String senderAddress;
    @SerializedName("totalSum")
    @Expose
    private Integer totalSum;
    @SerializedName("taxationType")
    @Expose
    private Integer taxationType;
    @SerializedName("userInn")
    @Expose
    private String userInn;


    public String getRetailPlaceAddress() {
        return retailPlaceAddress;
    }

    public void setRetailPlaceAddress(String retailPlaceAddress) {
        this.retailPlaceAddress = retailPlaceAddress;
    }

    public String getKktRegId() {
        return kktRegId;
    }

    public void setKktRegId(String kktRegId) {
        this.kktRegId = kktRegId;
    }

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getNds18() {
        return nds18;
    }

    public void setNds18(Integer nds18) {
        this.nds18 = nds18;
    }

    public Integer getEcashTotalSum() {
        return ecashTotalSum;
    }

    public void setEcashTotalSum(Integer ecashTotalSum) {
        this.ecashTotalSum = ecashTotalSum;
    }

    public Integer getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(Integer receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public Integer getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(Integer requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Integer getCashTotalSum() {
        return cashTotalSum;
    }

    public void setCashTotalSum(Integer cashTotalSum) {
        this.cashTotalSum = cashTotalSum;
    }

    public Integer getNds10() {
        return nds10;
    }

    public void setNds10(Integer nds10) {
        this.nds10 = nds10;
    }

    public String getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(String fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public Integer getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(Integer fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(Integer shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Integer getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Integer totalSum) {
        this.totalSum = totalSum;
    }

    public Integer getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(Integer taxationType) {
        this.taxationType = taxationType;
    }

    public String getUserInn() {
        return userInn;
    }

    public void setUserInn(String userInn) {
        this.userInn = userInn;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "retailPlaceAddress='" + retailPlaceAddress + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", totalSum=" + totalSum +
                '}';
    }
}
