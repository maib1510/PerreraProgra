package Recursividad;

import Domain.Adopcion;
import Domain.Animal;
import Domain.Gato;
import Domain.Pajaro;
import Domain.Perro;
import Domain.Producto;
import Domain.Roedor;
import DB.GestorBD;

public class Recursividad {

    private Gato[] gatos;
    private Roedor[] roedores;
    private Pajaro[] pajaros;
    private Perro[] perros;
    private Adopcion[] adopciones;
    private Producto[] productos;
    private GestorBD gestor;

    public Recursividad(Gato[] gatos, Roedor[] roedores, Pajaro[] pajaros,
                               Perro[] perros, Adopcion[] adopciones,
                               Producto[] productos, GestorBD gestor) {
        this.gatos = gatos;
        this.roedores = roedores;
        this.pajaros = pajaros;
        this.perros = perros;
        this.adopciones = adopciones;
        this.productos = productos;
        this.gestor = gestor;
    }

    // ---------- helpers ----------
    public boolean noHayAnimalesGenerales() {
        return (gatos == null || gatos.length == 0)
                && (roedores == null || roedores.length == 0)
                && (pajaros == null || pajaros.length == 0)
                && (perros == null || perros.length == 0);
    }

    public boolean estaAdoptadoGeneral(Animal a) {
        if (a == null) return false;
        if (gestor != null) {
            return gestor.estaAdoptadoPorId(a.getId_animal());
        }
        return a.isAdoptado();
    }
    
    
    //---------------RECURSIVIDAD---------------

    // ---------- 1) BUSCAR POR NOMBRE ----------
    public Animal buscarAnimalPorNombreRec(String nombre) {
        if (nombre == null) return null;
        String obj = nombre.trim();
        if (obj.isEmpty()) return null;

        Animal[][] grupos = new Animal[][]{gatos, roedores, pajaros, perros};
        return buscarPorNombreEnGruposRec(grupos, 0, 0, obj);
    }

    private Animal buscarPorNombreEnGruposRec(Animal[][] grupos, int gIdx, int idx, String nombre) {
        if (grupos == null || gIdx >= grupos.length) return null;
        Animal[] grupo = grupos[gIdx];

        if (grupo == null || idx >= grupo.length) {
            return buscarPorNombreEnGruposRec(grupos, gIdx + 1, 0, nombre);
        }

        Animal actual = grupo[idx];
        if (actual != null && actual.getNombre() != null
                && actual.getNombre().equalsIgnoreCase(nombre)) {
            return actual;
        }

        return buscarPorNombreEnGruposRec(grupos, gIdx, idx + 1, nombre);
    }

    // ---------- 2) TODOS DISPONIBLES / ADOPTADOS ----------
    public boolean todosDisponiblesRec() {
        Animal[][] grupos = new Animal[][]{gatos, roedores, pajaros, perros};
        return todosDisponiblesEnGruposRec(grupos, 0, 0);
    }

    private boolean todosDisponiblesEnGruposRec(Animal[][] grupos, int gIdx, int idx) {
        if (grupos == null || gIdx >= grupos.length) return true;
        Animal[] grupo = grupos[gIdx];

        if (grupo == null || idx >= grupo.length) {
            return todosDisponiblesEnGruposRec(grupos, gIdx + 1, 0);
        }

        Animal actual = grupo[idx];
        boolean cumple = (actual == null) || !estaAdoptadoGeneral(actual);
        return cumple && todosDisponiblesEnGruposRec(grupos, gIdx, idx + 1);
    }

    public boolean todosAdoptadosRec() {
        Animal[][] grupos = new Animal[][]{gatos, roedores, pajaros, perros};
        return todosAdoptadosEnGruposRec(grupos, 0, 0);
    }

    private boolean todosAdoptadosEnGruposRec(Animal[][] grupos, int gIdx, int idx) {
        if (grupos == null || gIdx >= grupos.length) return true;
        Animal[] grupo = grupos[gIdx];

        if (grupo == null || idx >= grupo.length) {
            return todosAdoptadosEnGruposRec(grupos, gIdx + 1, 0);
        }

        Animal actual = grupo[idx];
        boolean cumple = (actual == null) || estaAdoptadoGeneral(actual);
        return cumple && todosAdoptadosEnGruposRec(grupos, gIdx, idx + 1);
    }

    // ---------- 3) MÁS VIEJO / MÁS JOVEN ----------
    public Animal animalMasViejoRec() {
        Animal[][] grupos = new Animal[][]{gatos, roedores, pajaros, perros};
        return masViejoEnGruposRec(grupos, 0, 0, null);
    }

    private Animal masViejoEnGruposRec(Animal[][] grupos, int gIdx, int idx, Animal mejor) {
        if (grupos == null || gIdx >= grupos.length) return mejor;
        Animal[] grupo = grupos[gIdx];

        if (grupo == null || idx >= grupo.length) {
            return masViejoEnGruposRec(grupos, gIdx + 1, 0, mejor);
        }

        Animal actual = grupo[idx];
        if (actual != null) {
            if (mejor == null || actual.getEdad() > mejor.getEdad()) {
                mejor = actual;
            }
        }
        return masViejoEnGruposRec(grupos, gIdx, idx + 1, mejor);
    }

    public Animal animalMasJovenRec() {
        Animal[][] grupos = new Animal[][]{gatos, roedores, pajaros, perros};
        return masJovenEnGruposRec(grupos, 0, 0, null);
    }

    private Animal masJovenEnGruposRec(Animal[][] grupos, int gIdx, int idx, Animal mejor) {
        if (grupos == null || gIdx >= grupos.length) return mejor;
        Animal[] grupo = grupos[gIdx];

        if (grupo == null || idx >= grupo.length) {
            return masJovenEnGruposRec(grupos, gIdx + 1, 0, mejor);
        }

        Animal actual = grupo[idx];
        if (actual != null) {
            if (mejor == null || actual.getEdad() < mejor.getEdad()) {
                mejor = actual;
            }
        }
        return masJovenEnGruposRec(grupos, gIdx, idx + 1, mejor);
    }

    // ---------- 4) ADOPCIONES POR AÑO ----------
    public int contarAdopcionesPorAno(int ano) {
        return contarAdopcionesPorAnoRec(adopciones, 0, ano);
    }

    private int contarAdopcionesPorAnoRec(Adopcion[] lista_adopciones, int i, int ano) {
        if (lista_adopciones == null || i >= lista_adopciones.length) {
            return 0;
        }

        int suma = 0;
        Adopcion adopcion = lista_adopciones[i];
        if (adopcion != null && adopcion.getFecha_adopcion() != null) {
            if (adopcion.getFecha_adopcion().getYear() == ano) {
                suma = 1;
            }
        }

        return suma + contarAdopcionesPorAnoRec(lista_adopciones, i + 1, ano);
    }

    // ---------- 5) GASTO TOTAL TIENDA ----------
    public double gastoTotal() {
        return sumaPreciosRec(productos, 0);
    }

    private double sumaPreciosRec(Producto[] lista, int i) {
        if (lista == null || i >= lista.length) {
            return 0.0;
        }

        double precio = 0.0;
        if (lista[i] != null) {
            precio = lista[i].getPrecio();
        }

        return precio + sumaPreciosRec(lista, i + 1);
    }

    // ---------- 6) BUSCAR PRODUCTO POR NOMBRE ----------
    public Producto buscarProductoPorNombre(String nombre) {
        return buscarProductoPorNombreRec(productos, 0, nombre);
    }

    private Producto buscarProductoPorNombreRec(Producto[] lista_productos, int i, String nombre) {
        if (lista_productos == null || i >= lista_productos.length) {
            return null;
        }

        if (lista_productos[i] != null
                && lista_productos[i].getNombre().equals(nombre)) {
            return lista_productos[i];
        }

        return buscarProductoPorNombreRec(lista_productos, i + 1, nombre);
    }
}
