
package model;

import java.io.Serializable;

/**
 * @author Tran Quoc Cuong
 * 
 */


/**
 * Category DTO class
 * @version 1.0
 */
public class Category implements Serializable{
    /**
    * typeId - auto increasing in database
    * categoryName - name of category
    * memo - description of category
    */
    private int typeId;
    private String categoryName;
    private String memo;

    // Default constructor
    public Category() {
        this.typeId = 0;
        this.categoryName = "";
        this.memo = "";
    }

    // Contructors with attributes
    public Category(int typeId, String categoryName, String memo) {
        this.typeId = typeId;
        this.categoryName = categoryName;
        this.memo = memo;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    
}
