package com.bank.cps.inventory.document;

import com.bank.cps.common.document.BaseDocument;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("inventory_stock")
public class InventoryStockDocument extends BaseDocument {
    private String branchCode;
    private String chequeType;
    private String stockType;
    private Integer quantityAvailable;
    private Integer reorderLevel;
    private Instant lastRestockDate;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getChequeType() {
        return chequeType;
    }

    public void setChequeType(String chequeType) {
        this.chequeType = chequeType;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Instant getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(Instant lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }
}
