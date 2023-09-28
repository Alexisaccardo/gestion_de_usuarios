public class Personas {
    public String document;
    public String name;
    public int age;
    public String cellphone;
    public String address;

    public Personas(String document, String name, int age, String cellphone, String address) {
        this.document = document;
        this.name = name;
        this.age = age;
        this.cellphone = cellphone;
        this.address = address;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
