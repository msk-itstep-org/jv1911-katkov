package org.itstep.msk.app.entity;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date receiptDate;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "quantity_used")
    private Integer quantityUsed = 0;

    @ManyToOne(targetEntity = Ingredient.class)
    @JoinColumn(name = "ingredients_id", referencedColumnName = "id")
    private Ingredient ingredient;

    public Long getId() {
        return id;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Integer getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(Integer quantityUsed) {
        this.quantityUsed = quantityUsed;
    }
//    public Provider getProvider() {
//        return provider;
//    }
//
//    public void setProvider(Provider provider) {
//        this.provider = provider;
//    }
}
