import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Registro_personasTest {

    @Test
    public void TestEdit() {
        String document = "123";
        String name = "johan";
        int age = 23;
        String cellphone = "3132223486";
        String address = "medellin";

        Registro_personas registro_personas = new Registro_personas();

        Personas person = new Personas(document, name, age, cellphone, address);
        List<Personas> listperson = new ArrayList<>();

        listperson.add(person);

        String resultado = "USUARIO EDITADO CON EXITO: " + listperson.get(0).getDocument();

    }

    @Test
    public void TestEliminar() {
        String document = "123";
        String name = "johan";
        int age = 23;
        String cellphone = "3132223486";
        String address = "medellin";

        Registro_personas registro_personas = new Registro_personas();

        Personas person = new Personas(document, name, age, cellphone, address);
        List<Personas> listperson = new ArrayList<>();

        listperson.add(person);

        String document2 = "";

    }

    @Test
    public void TestInsert() {
        String document = "123";
        String name = "johan";
        int age = 23;
        String cellphone = "3132223486";
        String address = "medellin";

        Registro_personas registro_personas = new Registro_personas();

        Personas person = new Personas(document, name, age, cellphone, address);
        List<Personas> listperson = new ArrayList<>();

        listperson.add(person);

        String insert = "Se ha agregado con exito el empleado: " + listperson.get(0).getName();
    }
}