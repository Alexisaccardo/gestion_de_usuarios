import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registro_personas {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("*****GESTION DE SISTEMAS DE USUARIOS*****");

        System.out.println("Cuantos usuarios trabajan en la empresa?: ");
        int count = Integer.parseInt(scanner.nextLine());

        List<Personas> listapersonas = new ArrayList<>(count);

        for (int i = 1; i <= count; i++){

            System.out.println("Ingrese el documento de identidad del usuario #: "+i);
            String document = scanner.nextLine();

            System.out.println("Ingrese el nombre del usuario: ");
            String name = scanner.nextLine();

            System.out.println("Ingrese la edad del usuario: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese el numero de celular del usuario: ");
            String cellphone = scanner.nextLine();

            System.out.println("Ingrese la direccion del usuario: ");
            String address = scanner.nextLine();

            if (document.equals("") || name.equals("") || age <=  0 || cellphone.equals("") || address.equals("") ){
                System.out.println("Uno o mas campos no cumplen con los criterios minimos");
                i--;
            }else {
                Personas personas = new Personas(document, name, age, cellphone, address);
                listapersonas.add(personas);
            }
        }

        System.out.println("Se han agregado con exito una cantidad de "+count+ " usuarios.");

        boolean aux = true;

        while (aux) {

            System.out.println("1. Modificar la lista con sus usuarios: ");
            System.out.println("2. Eliminar usuario de la lista: ");
            System.out.println("3. Terminar y almacenar en base de datos: ");

            System.out.println("Ingrese un numero entre 1 - 3: ");
            int result = Integer.parseInt(scanner.nextLine());

            switch (result) {
                case 1:
                    System.out.println("Ingrese el documento del usuario a modificar: ");
                    String document = scanner.nextLine();

                    System.out.println("Ingrese el nuevo nombre (si aplica): ");
                    String name = scanner.nextLine();

                    System.out.println("Ingrese la nueva edad del usuario (si aplica): ");
                    int age = Integer.parseInt(scanner.nextLine());

                    System.out.println("Ingrese el numero del usuario (si aplica): ");
                    String cellphone = scanner.nextLine();

                    System.out.println("Ingrese la nueva direccion del usuario (si aplica): ");
                    String address = scanner.nextLine();

                    Personas personas = new Personas(document, name, age, cellphone, address);
                    Edit(listapersonas, personas);

                    for (int i = 0; i < listapersonas.size(); i++){
                        System.out.println("Personas almacenadas hasta el momento: "+listapersonas.get(i).getName());
                    }
                    break;

                case 2:
                    System.out.println("Ingrese numero de documento de la persona a la que deseas eliminar: ");
                    String document_delete = scanner.nextLine();

                    Eliminar(listapersonas, document_delete);

                    for (int i = 0; i < listapersonas.size(); i++){
                        System.out.println("Personas restantes almacenadas: "+listapersonas.get(i).getName());
                    }

                    break;
                case 3:
                    aux = false;
                    break;
            }
        }
        Insert(listapersonas);
    }

    public static void Edit(List<Personas> listapersonas, Personas personas) {

        for (int i = 0; i < listapersonas.size(); i++) {

            if (personas.getDocument().equals(listapersonas.get(i).getDocument())){
                listapersonas.get(i).setName(personas.getName());
                listapersonas.get(i).setAddress(personas.getAddress());
                listapersonas.get(i).setAge(personas.getAge());
                listapersonas.get(i).setCellphone(personas.getCellphone());
                listapersonas.get(i).setDocument(personas.getDocument());
                System.out.println("USUARIO EDITADO CON EXITO: "+listapersonas.get(i).getDocument());
            }
        }
    }

    public static void Eliminar(List<Personas> list, String document_delete){


        for (int i=0; i < list.size(); i++){
            if (document_delete.equals(list.get(i).getDocument())){
                list.remove(i);
            }
        }

    }

    public static void Insert(List<Personas> listapersonas){

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/personas";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");

            // Preparar la sentencia
            for (int i = 0; i < listapersonas.size(); i++){
                String sql = "INSERT INTO usuarios (documento, nombre, edad, celular, direccion) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, listapersonas.get(i).getDocument());
                preparedStatement.setString(2, listapersonas.get(i).getName());
                preparedStatement.setInt(3, listapersonas.get(i).getAge());
                preparedStatement.setString(4, listapersonas.get(i).getCellphone());
                preparedStatement.setString(5, listapersonas.get(i).getAddress());

                // Ejecutar la sentencia
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Se ha agregado con exito el usuario: "+listapersonas.get(i).getName());
                } else {
                    System.out.println("No se pudo agregar el usuario: "+listapersonas.get(i).getName());
                }
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}