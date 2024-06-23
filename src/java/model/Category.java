
package model;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class Category implements Serializable{
    private int typeId;
    private String categoryName;
    private String memo;

    public Category() {
        // 1 line
    }

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
