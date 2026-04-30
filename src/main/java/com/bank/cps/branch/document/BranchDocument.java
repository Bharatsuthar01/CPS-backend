package com.bank.cps.branch.document;

import com.bank.cps.common.document.BaseDocument;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("branches")
public class BranchDocument extends BaseDocument {
    private String code;
    private String name;
    private String description;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
