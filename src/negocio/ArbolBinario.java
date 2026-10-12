package negocio;

import modelo.Nodo;

public class ArbolBinario<T> {
    private Nodo<T> raiz;

    public Nodo<T> crearRaiz(T dato) {
        raiz = new Nodo<>(dato);
        return raiz;
    }

    // Solo agrega si el lado izquierdo del padre está libre; si no, devuelve false.
    public boolean agregarIzquierdo(Nodo<T> padre, T dato) {
        if (padre.getIzquierdo() != null) return false;
        padre.setIzquierdo(new Nodo<>(dato));
        return true;
    }

    // Solo agrega si el lado derecho del padre está libre; si no, devuelve false.
    public boolean agregarDerecho(Nodo<T> padre, T dato) {
        if (padre.getDerecho() != null) return false;
        padre.setDerecho(new Nodo<>(dato));
        return true;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public int contarNodos() {
        return contarNodos(raiz);
    }

    // Recursivo. Caso base: un nodo nulo no cuenta (0).
    // Si existe, cuenta 1 (él mismo) más los nodos del lado izquierdo y del derecho.
    private int contarNodos(Nodo<T> n) {
        if (n == null) return 0;
        return 1 + contarNodos(n.getIzquierdo()) + contarNodos(n.getDerecho());
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    // Recursivo. Caso base: nodo nulo = 0 hojas; nodo sin hijos = 1 hoja.
    // Si tiene hijos, suma las hojas de su lado izquierdo y de su lado derecho.
    private int contarHojas(Nodo<T> n) {
        if (n == null) return 0;
        if (n.esHoja()) return 1;
        return contarHojas(n.getIzquierdo()) + contarHojas(n.getDerecho());
    }

    public int altura() {
        return altura(raiz);
    }

    // Recursivo. Caso base: nodo nulo = -1, así una hoja da 0 y el árbol vacío da -1.
    // Si existe, suma 1 (la arista hacia sus hijos) a la altura del lado más alto.
    private int altura(Nodo<T> n) {
        if (n == null) return -1;
        return 1 + Math.max(altura(n.getIzquierdo()), altura(n.getDerecho()));
    }

    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        System.out.println(raiz.getDato());
        mostrar(raiz, "");
    }

    // Imprime los hijos con ramas ├── y └──; el prefijo mantiene la sangría de cada nivel.
    private void mostrar(Nodo<T> n, String prefijo) {
        Nodo<T> izq = n.getIzquierdo();
        Nodo<T> der = n.getDerecho();
        if (izq != null) {
            System.out.println(prefijo + (der != null ? "├── " : "└── ") + izq.getDato());
            mostrar(izq, prefijo + (der != null ? "│   " : "    "));
        }
        if (der != null) {
            System.out.println(prefijo + "└── " + der.getDato());
            mostrar(der, prefijo + "    ");
        }
    }
}
