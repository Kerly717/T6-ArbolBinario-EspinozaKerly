package negocio;

import modelo.Nodo;

public class ArbolBinario<T> {
    private Nodo<T> raiz;

    public Nodo<T> crearRaiz(T dato) {
        raiz = new Nodo<>(dato);
        return raiz;
    }

    public boolean agregarIzquierdo(Nodo<T> padre, T dato) {
        if (padre.getIzquierdo() != null) return false;
        padre.setIzquierdo(new Nodo<>(dato));
        return true;
    }

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

    private int contarNodos(Nodo<T> n) {
        if (n == null) return 0;
        return 1 + contarNodos(n.getIzquierdo()) + contarNodos(n.getDerecho());
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> n) {
        if (n == null) return 0;
        if (n.esHoja()) return 1;
        return contarHojas(n.getIzquierdo()) + contarHojas(n.getDerecho());
    }

    public int altura() {
        return altura(raiz);
    }

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