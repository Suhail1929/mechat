package com.asynctaskcoffee.ui.db.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

@Entity(
        tableName = "customer"
)
public final class Customer implements Serializable {
    @PrimaryKey(
            autoGenerate = true
    )
    private int customerId;
    @ColumnInfo(
            name = "user_name"
    )
    @Nullable
    private String userName;
    @ColumnInfo(
            name = "password"
    )
    @Nullable
    private String password;
    @ColumnInfo(
            name = "first_name"
    )
    @Nullable
    private String firstName;
    @ColumnInfo(
            name = "last_name"
    )
    @Nullable
    private String lastName;
    @ColumnInfo(
            name = "address"
    )
    @Nullable
    private String address;
    @ColumnInfo(
            name = "city"
    )
    @Nullable
    private String city;
    @ColumnInfo(
            name = "postal_code"
    )
    @Nullable
    private String postalCode;
    @ColumnInfo(
            name = "telephone"
    )
    @Nullable
    private String telephone;
    @ColumnInfo(
            name = "email"
    )
    @Nullable
    private String email;

    public final int getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(int var1) {
        this.customerId = var1;
    }

    @Nullable
    public final String getUserName() {
        return this.userName;
    }

    public final void setUserName(@Nullable String var1) {
        this.userName = var1;
    }

    @Nullable
    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(@Nullable String var1) {
        this.password = var1;
    }

    @Nullable
    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(@Nullable String var1) {
        this.firstName = var1;
    }

    @Nullable
    public final String getLastName() {
        return this.lastName;
    }

    public final void setLastName(@Nullable String var1) {
        this.lastName = var1;
    }

    @Nullable
    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(@Nullable String var1) {
        this.address = var1;
    }

    @Nullable
    public final String getCity() {
        return this.city;
    }

    public final void setCity(@Nullable String var1) {
        this.city = var1;
    }

    @Nullable
    public final String getPostalCode() {
        return this.postalCode;
    }

    public final void setPostalCode(@Nullable String var1) {
        this.postalCode = var1;
    }

    @Nullable
    public final String getTelephone() {
        return this.telephone;
    }

    public final void setTelephone(@Nullable String var1) {
        this.telephone = var1;
    }

    @Nullable
    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(@Nullable String var1) {
        this.email = var1;
    }
}
