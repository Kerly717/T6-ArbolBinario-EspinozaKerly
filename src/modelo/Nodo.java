package modelo;

// Nodo genérico del árbol binario: guarda un dato y referencias a sus dos hijos.
public class Nodo<T> {
    private T dato;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;

    public Nodo(T dato) {
        this.dato = dato;
    }

    // Un nodo es hoja cuando no tiene ningún hijo (ni izquierdo ni derecho).
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    // El grado es el número de hijos que tiene el nodo: 0, 1 o 2.
    public int grado() {
        int g = 0;
        if (izquierdo != null) g++;
        if (derecho != null) g++;
        return g;
    }

    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }

    public Nodo<T> getIzquierdo() { return izquierdo; }
    public void setIzquierdo(Nodo<T> izquierdo) { this.izquierdo = izquierdo; }

    public Nodo<T> getDerecho() { return derecho; }
    public void setDerecho(Nodo<T> derecho) { this.derecho = derecho; }
}
