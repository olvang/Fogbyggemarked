package FunctionLayer;

import Components.*;

public class Customer {

    private NameComponent nameComponent;
    private AddressComponent adresseComponent;
    private EmailComponent emailCompononent;
    private PhoneComponent phoneComponent;
    private ZipCodeComponent zipcodeComponent;

    public Customer(NameComponent nameCom, AddressComponent adresseCom, EmailComponent emailCom, PhoneComponent phoneCom,
                    ZipCodeComponent zipCom){

        this.nameComponent = nameCom;
        this.adresseComponent = adresseCom;
        this.emailCompononent = emailCom;
        this.phoneComponent = phoneCom;
        this.zipcodeComponent = zipCom;

    }

    public NameComponent getNameComponent() {
        return nameComponent;
    }

    public String getName(){
        return nameComponent.getName();
    }

    public void setNameComponent(NameComponent nameComponent) {
        this.nameComponent = nameComponent;
    }

    public AddressComponent getAdresseComponent() {
        return adresseComponent;
    }

    public String getAdresse(){
        return adresseComponent.getAddress();
    }

    public void setAdresseComponent(AddressComponent adresseComponent) {
        this.adresseComponent = adresseComponent;
    }

    public EmailComponent getEmailCompononent() {
        return emailCompononent;
    }

    public String getEmail(){
        return emailCompononent.getEmail();
    }

    public void setEmailCompononent(EmailComponent emailCompononent) {
        this.emailCompononent = emailCompononent;
    }

    public PhoneComponent getPhoneComponent() {
        return phoneComponent;
    }

    public String getPhone(){
        return phoneComponent.getPhone();
    }

    public void setPhoneComponent(PhoneComponent phoneComponent) {
        this.phoneComponent = phoneComponent;
    }

    public ZipCodeComponent getZipcodeComponent() {
        return zipcodeComponent;
    }

    public String getZipcode(){
        return zipcodeComponent.getZip();
    }

    public void ZipCodeComponent(ZipCodeComponent zipcodeComponent) {
        this.zipcodeComponent = zipcodeComponent;
    }

    @Override
    public String toString() {
        return nameComponent.getName() + " " + adresseComponent.getAddress() + " " + emailCompononent.getEmail() + " "
                + phoneComponent.getPhone() + " " + zipcodeComponent.getZip();
    }
}
