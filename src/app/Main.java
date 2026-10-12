package app;

import modelo.Nodo;
import negocio.ArbolBinario;

import java.util.Scanner;

public class Main {

    private static ArbolBinario<String> arbol = new ArbolBinario<>();
    private static Nodo<String> raiz = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Crear el árbol inicial");
            System.out.println("2. Visualizar el árbol");
            System.out.println("3. Consultar el número de nodos");
            System.out.println("4. Consultar el número de hojas");
            System.out.println("5. Consultar la altura");
            System.out.println("6. Consultar el grado de un nodo");
            System.out.println("7. Verificar si un nodo es hoja");
            System.out.println("8. Salir");
            System.out.print("Opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = 0;
            }
            switch (opcion) {
                case 1: crearArbolInicial(); break;
                case 2: arbol.mostrarArbol(); break;
                case 3: System.out.println("Nodos: " + arbol.contarNodos()); break;
                case 4: System.out.println("Hojas: " + arbol.contarHojas()); break;
                case 5: System.out.println("Altura: " + arbol.altura()); break;
                case 6:
                    Nodo<String> a = pedirNodo(sc);
                    if (a != null) System.out.println("Grado de " + a.getDato() + ": " + a.grado());
                    break;
                case 7:
                    Nodo<String> b = pedirNodo(sc);
                    if (b != null) System.out.println(b.getDato() + (b.esHoja() ? " es hoja." : " no es hoja."));
                    break;
                case 8: System.out.println("Fin del programa."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 8);
        sc.close();
    }

    // Arma el árbol inicial: primero la raíz, luego los hijos de cada nivel de arriba hacia abajo.
    // Se guarda la referencia de cada nodo padre para poder agregarle sus hijos.
    private static void crearArbolInicial() {
        arbol = new ArbolBinario<>();
        raiz = arbol.crearRaiz("UNIVERSIDAD");
        arbol.agregarIzquierdo(raiz, "FISEI");
        arbol.agregarDerecho(raiz, "DIDE");
        Nodo<String> fisei = raiz.getIzquierdo();
        Nodo<String> dide = raiz.getDerecho();
        arbol.agregarIzquierdo(fisei, "SOFTWARE");
        arbol.agregarDerecho(fisei, "TI");
        arbol.agregarIzquierdo(dide, "INVEST.");
        arbol.agregarDerecho(dide, "VINCULACIÓN");
        Nodo<String> software = fisei.getIzquierdo();
        arbol.agregarIzquierdo(software, "NIVEL1");
        arbol.agregarDerecho(software, "NIVEL3");
        System.out.println("Árbol inicial creado.");
    }

    private static Nodo<String> pedirNodo(Scanner sc) {
        System.out.print("Nombre del nodo: ");
        Nodo<String> n = buscar(raiz, sc.nextLine().trim());
        if (n == null) System.out.println("Nodo no encontrado.");
        return n;
    }

    // Búsqueda recursiva por nombre: revisa el nodo, luego el lado izquierdo y después el derecho.
    private static Nodo<String> buscar(Nodo<String> n, String nombre) {
        if (n == null) return null;
        if (n.getDato().equalsIgnoreCase(nombre)) return n;
        Nodo<String> izq = buscar(n.getIzquierdo(), nombre);
        return izq != null ? izq : buscar(n.getDerecho(), nombre);
    }
}
