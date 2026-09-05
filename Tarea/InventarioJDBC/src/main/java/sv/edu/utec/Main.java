package sv.edu.utec;
import sv.edu.utec.datos.VehiculoDAO;
import sv.edu.utec.modelo.Vehiculo;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehiculoDAO dao = new VehiculoDAO();

        System.out.println("*** 1. CREANDO TABLA DE VEHÍCULOS ***");
        dao.crearTabla();
        System.out.println("Tabla verificada / creada exitosamente.\n");

        System.out.println("*** 2. INSERTANDO VEHÍCULOS ***");
        Vehiculo v1 = new Vehiculo(1, "Toyota", "Corolla", 2020);
        Vehiculo v2 = new Vehiculo(2, "Honda", "CR-V", 2024);
        Vehiculo v3 = new Vehiculo(3, "Hyundai", "Elantra", 2021);

        dao.insertar(v1);
        dao.insertar(v2);
        dao.insertar(v3);
        System.out.println("Vehículos insertados correctamente.\n");

        System.out.println("*** 3. LISTADO INICIAL DE VEHÍCULOS ***");
        imprimirLista(dao.listar());

        System.out.println("\n*** 4. ACTUALIZANDO UN VEHÍCULO ***");
        Vehiculo vActualizado = new Vehiculo(2, "Honda", "CR-V", 2024);
        boolean actualizado = dao.actualizar(vActualizado);
        if (actualizado) {
            System.out.println("Vehículo con ID 2 actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar el vehículo.");
        }

        System.out.println("\n*** LISTADO TRAS LA ACTUALIZACIÓN ***");
        imprimirLista(dao.listar());

        System.out.println("\n*** 5. ELIMINANDO UN VEHÍCULO ***");
        boolean eliminado = dao.eliminar(3);
        if (eliminado) {
            System.out.println("Vehículo con ID 3 eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el vehículo.");
        }

        System.out.println("\n*** LISTADO FINAL EN LA BASE DE DATOS ***");
        imprimirLista(dao.listar());
    }


    private static void imprimirLista(List<Vehiculo> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
            return;
        }
        for (Vehiculo v : lista) {
            System.out.println("ID: " + v.getId() +
                    " | Marca: " + v.getMarca() +
                    " | Modelo: " + v.getModelo() +
                    " | Año: " + v.getAnio());
        }
    }
}