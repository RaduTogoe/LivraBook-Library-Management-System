package elemente;

/**
 * Clasa Utilizator reprezintă un utilizator al bibliotecii.
 */
public class Utilizator {
    private String nume;
    private String prenume;
    private String dataNasterii;
    private String email;
    private String numarTelefon;
    private String adresa;
    private String username;
    private String parola;
    private boolean isAdmin;

    /**
     * Constructor implicit pentru clasa Utilizator.
     */
    public Utilizator() {

    }

    /**
     * Constructor pentru clasa Utilizator cu parametri.
     *
     * @param nume          Numele utilizatorului.
     * @param prenume       Prenumele utilizatorului.
     * @param dataNasterii  Data de naștere a utilizatorului.
     * @param email         Adresa de email a utilizatorului.
     * @param numarTelefon  Numărul de telefon al utilizatorului.
     * @param adresa        Adresa utilizatorului.
     * @param username      Numele de utilizator.
     * @param parola        Parola utilizatorului.
     */
    public Utilizator(String nume, String prenume, String dataNasterii, String email, String numarTelefon,
            String adresa, String username, String parola) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.email = email;
        this.numarTelefon = numarTelefon;
        this.adresa = adresa;
        this.username = username;
        this.parola = parola;
        this.isAdmin = false;
    }

    /**
     * Constructor pentru clasa Utilizator cu parametri pentru autentificare.
     *
     * @param username Numele de utilizator.
     * @param parola   Parola utilizatorului.
     */
    public Utilizator(String username, String parola) {
        this.username = username;
        this.parola = parola;
    }

    /**
     * Verifică dacă datele de autentificare sunt corecte.
     *
     * @param username Numele de utilizator.
     * @param parola   Parola utilizatorului.
     * @return True dacă datele sunt corecte, false în caz contrar.
     */
    public boolean verificaDate(String username, String parola) {
        return (this.username.equals(username) && this.parola.equals(parola));
    }

    /**
     * Schimbă statutul de administrator al utilizatorului.
     *
     * @param status Noul statut de administrator.
     */
    public void changeAdmin(boolean status) {
        this.isAdmin = status;
    }

    /**
     * Redefinește metoda toString pentru a returna informații despre utilizator.
     *
     * @return Șir de caractere care reprezintă informațiile despre utilizator.
     */
    @Override
    public String toString() {
        return username + " " + parola;
    }

    // Metode de acces pentru a obține informații despre utilizator

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getDataNasterii() {
        return dataNasterii;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getUsername() {
        return username;
    }

    public String getParola() {
        return parola;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    // Metode de modificare pentru a actualiza informațiile despre utilizator

    public void setNume(String s) {
        nume = s;
    }

    public void setPrenume(String s) {
        prenume = s;
    }

    public void setAdresa(String s) {
        adresa = s;
    }

    public void setParola(String s) {
        parola = s;
    }

    public void setNumarTelefon(String s) {
        numarTelefon = s;
    }

    public void setEmail(String s) {
        email = s;
    }

    public void setDataNasterii(String s) {
        dataNasterii = s;
    }

    public void setUsername(String s) {
        username = s;
    }

    /**
     * Redefinește metoda equals pentru a compara doi utilizatori.
     *
     * @param obj Obiectul de comparat cu utilizatorul curent.
     * @return True dacă obiectele sunt egale, false în caz contrar.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Utilizator))
            return false;
        Utilizator user = (Utilizator) obj;

        return this.username.equals(user.getUsername()) && this.parola.equals(user.getParola());
    }
}
