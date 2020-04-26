package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ingredients_storage")
public class IngredientStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (columnDefinition = "int unsigned")
    private Long id;

    @Column(name = "receipt_date")
    @Temporal(TemporalType.DATE)
    private Date receiptDate;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "price_for_kilo")
    private Integer priceForKilo;

    @ManyToOne(targetEntity = Ingredient.class)
    @JoinColumn(name = "ingredients_id", referencedColumnName = "id")
    private Ingredient ingredient;

//    @OneToOne(targetEntity = Provider.class)
//    private Provider provider;

    public Long getId() {
        return id;
    }

    public Date getReceiptDate() {
        return receiptDate;
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

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }
//    public Provider getProvider() {
//        return provider;
//    }
//
//    public void setProvider(Provider provider) {
//        this.provider = provider;
//    }
}
