/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "categories", catalog = "ProductIntro", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c")
    , @NamedQuery(name = "Categories.findByTypeId", query = "SELECT c FROM Categories c WHERE c.typeId = :typeId")
    , @NamedQuery(name = "Categories.findByCategoryName", query = "SELECT c FROM Categories c WHERE c.categoryName = :categoryName")
    , @NamedQuery(name = "Categories.findByMemo", query = "SELECT c FROM Categories c WHERE c.memo = :memo")})
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "typeId", nullable = false)
    private Integer typeId;
    @Basic(optional = false)
    @Column(name = "categoryName", nullable = false, length = 88)
    private String categoryName;
    @Column(name = "memo", length = 1073741823)
    private String memo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    private Collection<Products> productsCollection;

    public Categories() {
    }

    public Categories(Integer typeId) {
        this.typeId = typeId;
    }

    public Categories(Integer typeId, String categoryName) {
        this.typeId = typeId;
        this.categoryName = categoryName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
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

    @XmlTransient
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categories[ typeId=" + typeId + " ]";
    }
    
}
