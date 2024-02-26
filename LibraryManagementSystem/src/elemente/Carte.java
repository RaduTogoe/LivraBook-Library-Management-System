package elemente;

/**
 * Clasa Carte reprezintă o carte din bibliotecă.
 */
public class Carte {
    private String titlu;
    private String autor;
    private String editura;
    private String categorie;
    private String ISBN;
    private int nrExemplareDisponibile;
    private int nrExemplareTotale;
    private int anulAparitiei;

    /**
     * Constructor pentru clasa Carte cu parametri.
     *
     * @param titlu                 Titlul cărții.
     * @param autor                 Autorul cărții.
     * @param editura               Editura cărții.
     * @param categorie             Categorie sau genul cărții.
     * @param ISBN                  Codul ISBN al cărții.
     * @param nrExemplareDisponibile Numărul de exemplare disponibile în bibliotecă.
     * @param nrExemplareTotale     Numărul total de exemplare al cărții.
     * @param anulAparitiei         Anul în care a fost publicată cartea.
     */
    public Carte(String titlu, String autor, String editura, String categorie, String ISBN, int nrExemplareDisponibile,
            int nrExemplareTotale, int anulAparitiei) {
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.categorie = categorie;
        this.ISBN = ISBN;
        this.nrExemplareDisponibile = nrExemplareDisponibile;
        this.nrExemplareTotale = nrExemplareTotale;
        this.anulAparitiei = anulAparitiei;
    }

    /**
     * Metodă pentru a obține titlul cărții.
     *
     * @return Titlul cărții.
     */
    public String getTitlu() {
        return this.titlu;
    }

    /**
     * Metodă pentru a obține autorul cărții.
     *
     * @return Autorul cărții.
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * Metodă pentru a obține editura cărții.
     *
     * @return Editura cărții.
     */
    public String getEditura() {
        return this.editura;
    }

    /**
     * Metodă pentru a obține categoria/genul cărții.
     *
     * @return Categoria/genul cărții.
     */
    public String getCategorie() {
        return this.categorie;
    }

    /**
     * Metodă pentru a obține codul ISBN al cărții.
     *
     * @return Codul ISBN al cărții.
     */
    public String getISBN() {
        return this.ISBN;
    }

    /**
     * Metodă pentru a obține numărul de exemplare disponibile în bibliotecă.
     *
     * @return Numărul de exemplare disponibile.
     */
    public int getNrExemplareDisponibile() {
        return this.nrExemplareDisponibile;
    }

    /**
     * Metodă pentru a obține numărul total de exemplare al cărții.
     *
     * @return Numărul total de exemplare al cărții.
     */
    public int getNrExemplareTotale() {
        return this.nrExemplareTotale;
    }

    /**
     * Metodă pentru a obține anul în care a fost publicată cartea.
     *
     * @return Anul publicării cărții.
     */
    public int getAnulAparitiei() {
        return this.anulAparitiei;
    }

    /**
     * Metodă pentru a converti obiectul Carte la șirul de caractere.
     *
     * @return Șirul de caractere reprezentând detaliile cărții.
     */
    @Override
    public String toString() {
        return titlu + " " + autor + " " + editura;
    }

    /**
     * Metodă pentru a verifica dacă un alt obiect este egal cu această carte.
     *
     * @param obj Obiectul de comparat.
     * @return `true` dacă obiectele sunt egale, `false` altfel.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Carte))
            return false;
        Carte carte = (Carte) obj;

        return this.titlu.equals(carte.getTitlu()) && this.autor.equals(carte.getAutor())
                && this.categorie.equals(carte.getCategorie()) && this.ISBN.equals(carte.getISBN())
                && this.nrExemplareDisponibile == carte.getNrExemplareDisponibile()
                && this.nrExemplareTotale == carte.getNrExemplareTotale()
                && this.anulAparitiei == carte.getAnulAparitiei();
    }
}
