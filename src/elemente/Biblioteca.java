package elemente;

import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import interfata.*;

/**
 * Clasa Biblioteca contine metode pentru gestionarea utilizatorilor, administratorilor, cartilor si imprumuturilor.
 */
public class Biblioteca {
    public static List<Utilizator> utilizatori;
    public static List<Admin> admini;
    public static List<Client> clienti;
    public static Client utilizatorLogat;
    public static Admin adminLogat;
    public static List<Carte> carti;

    /**
     * Converteste un array de string-uri in obiect de tip Client.
     *
     * @param data Array-ul de string-uri cu datele clientului.
     * @return Obiectul de tip Client creat.
     */
    public static Client arrayToClient(String[] data) {
        return new Client(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                Integer.parseInt(data[9]));
    }

    /**
     * Converteste un array de string-uri in obiect de tip Admin.
     *
     * @param data Array-ul de string-uri cu datele administratorului.
     * @return Obiectul de tip Admin creat.
     */
    public static Admin arrayToAdmin(String[] data) {
        return new Admin(data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
    }

    /**
     * Converteste un array de string-uri in obiect de tip Carte.
     *
     * @param data Array-ul de string-uri cu datele cartii.
     * @return Obiectul de tip Carte creat.
     */
    public static Carte arrayToCarte(String[] data) {
        return new Carte(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]),
                Integer.parseInt(data[6]), Integer.parseInt(data[7]));
    }

    /**
     * Citeste utilizatorii din fisier si ii adauga in listele corespunzatoare.
     */
    public static void citireUtilizatori() {
        File f = new File("src/elemente/utilizatori.txt");
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] data = line.split(";");
                if (data[0].equals("Client")) {
                    clienti.add(arrayToClient(data));
                    System.out.println("aaaa");
                } else if (data[0].equals("Admin"))
                    admini.add(arrayToAdmin(data));
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: src/elemente/utilizatori.txt");
        }
    }

    /**
     * Actualizeaza informatiile despre utilizatori in fisierul de stocare.
     */
    public static void actualizareUtilizatori() {
        File f = new File("src/elemente/utilizatori.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Client c : clienti) {
                writer.write("Client;");
                writer.write(c.getUsername() + ";");
                writer.write(c.getParola() + ";");
                writer.write(c.getNume() + ";");
                writer.write(c.getPrenume() + ";");
                writer.write(c.getDataNasterii() + ";");
                writer.write(c.getEmail() + ";");
                writer.write(c.getNumarTelefon() + ";");
                writer.write(c.getAdresa() + ";");
                writer.write(c.getTaxaPlata() + ";");
                writer.newLine();
            }
            for (Admin a : admini) {
                writer.write("Admin;");
                writer.write(a.getNume() + ";");
                writer.write(a.getPrenume() + ";");
                writer.write(a.getDataNasterii() + ";");
                writer.write(a.getEmail() + ";");
                writer.write(a.getNumarTelefon() + ";");
                writer.write(a.getAdresa() + ";");
                writer.write(a.getUsername() + ";");
                writer.write(a.getParola() + ";");
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualizeaza informatiile despre carti in fisierul de stocare.
     */
    public static void actualizareCarti() {
        File f = new File("src/elemente/carti.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Carte c : carti) {
                writer.write(c.getTitlu() + ";");
                writer.write(c.getAutor() + ";");
                writer.write(c.getEditura() + ";");
                writer.write(c.getCategorie() + ";");
                writer.write(c.getISBN() + ";");
                writer.write(c.getNrExemplareDisponibile() + ";");
                writer.write(c.getNrExemplareTotale() + ";");
                writer.write(c.getAnulAparitiei() + ";");
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Citeste cartile din fisier si le adauga in lista corespunzatoare.
     */
    public static void citireCarti() {
        File f = new File("src/elemente/carti.txt");
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] data = line.split(";");
                carti.add(arrayToCarte(data));
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: src/elemente/utilizatori.txt");
        }
    }

    /**
     * Citeste informatiile despre imprumuturi din fisier si le adauga in listele corespunzatoare.
     */
    public static void citireImprumuturi() {
        File f = new File("src/elemente/imprumuturi.txt");
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] data = line.split(";");
                    System.out.println(data[0]);
                for (Client c : clienti) {
                    if (c.getUsername().equals(data[0])) {
                        System.out.println("KEK");
                        for (Carte i : carti) {
                            if (i.getTitlu().equals(data[1]) && i.getAutor().equals(data[2])) {
                                boolean returnat = (data[6] == "1");


                                CarteImprumutata ci = new CarteImprumutata(i, data[3], data[4], data[5], returnat);
                                System.out.println(ci);
                                c.addCarteImprumutata(ci);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: src/elemente/utilizatori.txt");
        }
    }

    /**
     * Actualizeaza informatiile despre imprumuturi in fisierul de stocare.
     */
    public static void actualizareImprumuturi() {
        File f = new File("src/elemente/imprumuturi.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (Client c : clienti) {
                for (CarteImprumutata i : c.getCartiImprumutate()) {
                    writer.write(c.getUsername() + ";");
                    writer.write(i.getTitlu() + ";");
                    writer.write(i.getAutor() + ";");
                    writer.write(i.getDataImprumutului() + ";");
                    writer.write(i.getDataReturnarii() + ";");
                    writer.write(i.getDataScadenta() + ";");
                    if (i.getEsteReturnat() == true)
                        writer.write("1");
                    else
                        writer.write("0");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Punctul de intrare in aplicatie, initializeaza listele si afiseaza interfata HomeFrame.
     *
     * @param args Argumente de linie de comanda (neutilizate).
     */
    public static void startBiblioteca() {

        utilizatori = new ArrayList<>();
        clienti = new ArrayList<>();
        admini = new ArrayList<>();
        carti = new ArrayList<>();

        HomeFrame frame = new HomeFrame();
    }

}