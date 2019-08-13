package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "mail")
    private String mail;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "code")
    private String code;

    @Column(name = "confirm")
    private Boolean confirm;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Basket basket;

    public Order() {
    }

    public Order(String name, String surname, String mail, String phoneNumber, String address,
                 String code, Basket basket) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.code = code;
        this.basket = basket;
    }

    public Order(Long id, String name, String surname, String mail, String phoneNumber,
                 String address, String code, Basket basket) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.code = code;
        this.basket = basket;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(name, order.name) &&
                Objects.equals(surname, order.surname) &&
                Objects.equals(mail, order.mail) &&
                Objects.equals(phoneNumber, order.phoneNumber) &&
                Objects.equals(address, order.address) &&
                Objects.equals(code, order.code) &&
                Objects.equals(basket, order.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, mail, phoneNumber, address, code, basket);
    }
}
