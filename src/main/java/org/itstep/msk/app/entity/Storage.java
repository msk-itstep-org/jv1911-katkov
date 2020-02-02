package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receipt_date")
    private Date receiptDate;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "price_for_kilo")
    private Integer priceForKilo;

    @ManyToMany(targetEntity = Ingredient.class, mappedBy = "storages")
    private List<Ingredient> ingredients;

    @ManyToOne(targetEntity = Provider.class)
    @JoinColumn(name = "providers_id", referencedColumnName = "id")
    private Provider provider;

    public Long getId() {
        return id;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getPriceForKilo() {
        return priceForKilo;
    }

    public void setPriceForKilo(Integer priceForKilo) {
        this.priceForKilo = priceForKilo;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
