package elemente;

/**
 * Clasa Admin reprezintă un administrator al bibliotecii și extinde clasa Utilizator.
 */
public class Admin extends Utilizator {

    /**
     * Constructor implicit pentru clasa Admin.
     */
    public Admin() {
        super();
    }

    /**
     * Constructor pentru clasa Admin cu parametri.
     *
     * @param nume         Numele administratorului.
     * @param prenume      Prenumele administratorului.
     * @param dataNasterii Data de naștere a administratorului.
     * @param email        Adresa de email a administratorului.
     * @param numarTelefon Numărul de telefon al administratorului.
     * @param adresa       Adresa administratorului.
     * @param username     Numele de utilizator al administratorului.
     * @param parola       Parola administratorului.
     */
    public Admin(String nume, String prenume, String dataNasterii, String email, String numarTelefon, String adresa,
            String username, String parola) {
        super(nume, prenume, dataNasterii, email, numarTelefon, adresa, username, parola);
    }

    /**
     * Constructor pentru clasa Admin care primește un obiect Utilizator și creează un administrator pe baza acestuia.
     *
     * @param u Obiectul Utilizator care reprezintă baza pentru crearea administratorului.
     */
    public Admin(Utilizator u) {
        super(u.getNume(), u.getPrenume(), u.getDataNasterii(), u.getEmail(), u.getNumarTelefon(), u.getAdresa(),
                u.getUsername(), u.getParola());
    }
}
