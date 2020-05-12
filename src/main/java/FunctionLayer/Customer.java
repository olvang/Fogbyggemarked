package FunctionLayer;

public class Customer {

    private NameComponent nameComponent;
    private AdresseComponent adresseComponent;
    private EmailCompononent emailCompononent;
    private PhoneComponent phoneComponent;
    private ZipcodeComponent zipcodeComponent;

    public Customer(NameComponent nameCom, AdresseComponent adresseCom, EmailCompononent emailCom, PhoneComponent phoneCom,
                    ZipcodeComponent zipCom){

        this.nameComponent = nameCom;
        this.adresseComponent = adresseCom;
        this.emailCompononent = emailCom;
        this.phoneComponent = phoneCom;
        this.zipcodeComponent = zipCom;

    }

    public AdresseComponent getAdresseComponent() {
        return adresseComponent;
    }

    public void setAdresseComponent(AdresseComponent adresseComponent) {
        this.adresseComponent = adresseComponent;
    }

    public EmailCompononent getEmailCompononent() {
        return emailCompononent;
    }

    public void setEmailCompononent(EmailCompononent emailCompononent) {
        this.emailCompononent = emailCompononent;
    }

    public PhoneComponent getPhoneComponent() {
        return phoneComponent;
    }

    public void setPhoneComponent(PhoneComponent phoneComponent) {
        this.phoneComponent = phoneComponent;
    }

    public ZipcodeComponent getZipcodeComponent() {
        return zipcodeComponent;
    }

    public void setZipcodeComponent(ZipcodeComponent zipcodeComponent) {
        this.zipcodeComponent = zipcodeComponent;
    }

    public NameComponent getNameComponent() {
        return nameComponent;
    }

    public void setNameComponent(NameComponent nameComponent) {
        this.nameComponent = nameComponent;
    }

    @Override
    public String toString() {
        return nameComponent.getName + " " + adresseComponent.getAdresse + " " + emailCompononent.getEmail + " "
                + phoneComponent.getPhone + " " + zipcodeComponent.getZipcode;
    }
}
