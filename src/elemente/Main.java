package elemente;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Main {
	public static Connection conn;

	public static void creareTabelCarti(Statement stmt) {
		String sql = "CREATE TABLE carti ("
				+ "carte_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "titlu VARCHAR(255)," + "autor VARCHAR(255)," + "editura VARCHAR(255)," + "ISBN VARCHAR(255),"
				+ "categorie VARCHAR(255)," + "nr_exemplare_disponibile INT," + "nr_exemplare_totale INT,"
				+ "anul_aparitiei INT" + ")";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void creareTabelUseri(Statement stmt) {
		String sql = "CREATE TABLE utilizatori ("
				+ "user_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "nume VARCHAR(255)," + "prenume VARCHAR(255)," + "data_nasterii DATE," + "email VARCHAR(255),"
				+ "numar_telefon VARCHAR(255)," + "adresa VARCHAR(255)," + "username VARCHAR(255),"
				+ "parola VARCHAR(255)," + "taxa_plata INT," + "este_admin SMALLINT " + ")";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void creareTabelImprumuturi(Statement stmt) {
		String sql = "CREATE TABLE imprumuturi ("
				+ "imprumut_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ "user_id INT, " + "carte_id INT, " + "data_imprumutului DATE, " + "data_returnarii DATE, "
				+ "data_scadenta DATE, " + "este_returnat SMALLINT, "
				+ " FOREIGN KEY (user_id) REFERENCES Utilizatori(user_id), "
				+ " FOREIGN KEY (carte_id) REFERENCES Carti(carte_id) " + ")";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void importBooks(Connection conn) {
		String sql = "SELECT * FROM carti";

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Carte book = new Carte(rs.getString("titlu"), rs.getString("autor"), rs.getString("editura"),
						rs.getString("categorie"), rs.getString("ISBN"), rs.getInt("nr_exemplare_disponibile"),
						rs.getInt("nr_exemplare_totale"), rs.getInt("anul_aparitiei"));
				Biblioteca.carti.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void importUsers(Connection conn) {
		String sql = "SELECT * FROM utilizatori";

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				if (rs.getString("este_admin").equals("1")) {
					Admin a = new Admin(rs.getString("nume"), rs.getString("prenume"), rs.getString("data_nasterii"),
							rs.getString("email"), rs.getString("numar_telefon"), rs.getString("adresa"),
							rs.getString("username"), rs.getString("parola"));
					Biblioteca.admini.add(a);
					System.out.println(a.getNume());
				} else if (rs.getString("este_admin").equals("0")) {
					Biblioteca.clienti.add(new Client(rs.getString("username"), rs.getString("parola"),
							rs.getString("nume"), rs.getString("prenume"), rs.getString("data_nasterii"),
							rs.getString("email"), rs.getString("numar_telefon"), rs.getString("adresa"),
							Integer.parseInt(rs.getString("taxa_plata"))));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exportBooks(Connection conn) {
		// Query to check if a book with the same ISBN already exists
		String checkSql = "SELECT COUNT(*) FROM carti WHERE ISBN = ?";

		// Insert query
		String insertSql = "INSERT INTO carti (titlu, autor, editura, categorie, ISBN, nr_exemplare_disponibile, nr_exemplare_totale, anul_aparitiei) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		// Update query without setting ISBN in SET clause
		String updateSql = "UPDATE carti SET titlu = ?, autor = ?, editura = ?, categorie = ?, nr_exemplare_disponibile = ?, nr_exemplare_totale = ?, anul_aparitiei = ? WHERE ISBN = ?";

		try (PreparedStatement checkStmt = conn.prepareStatement(checkSql);
				PreparedStatement insertStmt = conn.prepareStatement(insertSql);
				PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

			for (Carte book : Biblioteca.carti) {
				// Check for existing book with the same ISBN
				checkStmt.setString(1, book.getISBN());
				ResultSet rs = checkStmt.executeQuery();
				if (rs.next() && rs.getInt(1) == 0) { // No existing book with this ISBN
					// Set parameters for the insert statement
					insertStmt.setString(1, book.getTitlu());
					insertStmt.setString(2, book.getAutor());
					insertStmt.setString(3, book.getEditura());
					insertStmt.setString(4, book.getCategorie());
					insertStmt.setString(5, book.getISBN());
					insertStmt.setInt(6, book.getNrExemplareDisponibile());
					insertStmt.setInt(7, book.getNrExemplareTotale());
					insertStmt.setInt(8, book.getAnulAparitiei());
					insertStmt.executeUpdate();
				} else {
					// Set parameters for the update statement
					updateStmt.setString(1, book.getTitlu());
					updateStmt.setString(2, book.getAutor());
					updateStmt.setString(3, book.getEditura());
					updateStmt.setString(4, book.getCategorie());
					updateStmt.setInt(5, book.getNrExemplareDisponibile());
					updateStmt.setInt(6, book.getNrExemplareTotale());
					updateStmt.setInt(7, book.getAnulAparitiei());
					updateStmt.setString(8, book.getISBN()); // Correctly setting ISBN in WHERE clause
					updateStmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exportUsers(Connection conn) {
		// Query to check if a user with the same username already exists
		String checkSql = "SELECT COUNT(*) FROM utilizatori WHERE username = ? AND parola = ?";
		String updateSql = "UPDATE utilizatori SET nume = ?, prenume = ?, adresa = ?, numar_telefon = ?, email = ?, data_nasterii = ?, username = ?, parola = ?, taxa_plata = ?, este_admin = ? WHERE username = ?";
		
		// Insert query
		String insertSql = "INSERT INTO utilizatori (nume, prenume, adresa, numar_telefon, email, data_nasterii, username, parola, taxa_plata, este_admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement checkStmt = conn.prepareStatement(checkSql);
				PreparedStatement insertStmt = conn.prepareStatement(insertSql);
						PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

			// Combine clients and admins in a single loop to avoid code duplication
			List<Client> clienti = new ArrayList<>(Biblioteca.clienti);
			clienti.addAll(Biblioteca.clienti);

			for (Client client : clienti) {
				// Check for existing user with the same username
				checkStmt.setString(1, client.getUsername());
				checkStmt.setString(2, client.getParola());
				ResultSet rs = checkStmt.executeQuery();
				if (rs.next() && rs.getInt(1) == 0) { // No existing user with this username
					insertStmt.setString(1, client.getNume());
					insertStmt.setString(2, client.getPrenume());
					insertStmt.setString(3, client.getAdresa());
					insertStmt.setString(4, client.getNumarTelefon());
					insertStmt.setString(5, client.getEmail());
					insertStmt.setString(6, client.getDataNasterii());
					insertStmt.setString(7, client.getUsername());
					insertStmt.setString(8, client.getParola());
					// Assuming taxaPlata is an integer. Use setInt for numeric values.
					insertStmt.setInt(9, client.getTaxaPlata());
					insertStmt.setInt(10, client instanceof Client ? 0 : 1); // Check if user is an instance of Admin
					insertStmt.executeUpdate();
				} else {
					updateStmt.setString(1, client.getNume());
					updateStmt.setString(2, client.getPrenume());
					updateStmt.setString(3, client.getAdresa());
					updateStmt.setString(4, client.getNumarTelefon());
					updateStmt.setString(5, client.getEmail());
					updateStmt.setString(6, client.getDataNasterii());
					updateStmt.setString(7, client.getUsername());
					updateStmt.setString(8, client.getParola());
					updateStmt.setInt(9, client.getTaxaPlata());
					updateStmt.setInt(10, client instanceof Client ? 0 : 1); // Check if user is an instance of Admin
					updateStmt.setString(11, client.getUsername());
					updateStmt.executeUpdate();
				}
			}

			List<Admin> admini = new ArrayList<>(Biblioteca.admini);
			clienti.addAll(Biblioteca.clienti);

			for (Admin admin : admini) {
				checkStmt.setString(1, admin.getUsername());
				checkStmt.setString(2, admin.getParola());
				ResultSet rs = checkStmt.executeQuery();
				if (rs.next() && rs.getInt(1) == 0) {
					insertStmt.setString(1, admin.getNume());
					insertStmt.setString(2, admin.getPrenume());
					insertStmt.setString(3, admin.getAdresa());
					insertStmt.setString(4, admin.getNumarTelefon());
					insertStmt.setString(5, admin.getEmail());
					insertStmt.setString(6, admin.getDataNasterii());
					insertStmt.setString(7, admin.getUsername());
					insertStmt.setString(8, admin.getParola());
					insertStmt.setInt(9, 0);
					insertStmt.setInt(10, admin instanceof Admin ? 1 : 0);
					insertStmt.executeUpdate();
				}
				else {
					updateStmt.setString(1, admin.getNume());
					updateStmt.setString(2, admin.getPrenume());
					updateStmt.setString(3, admin.getAdresa());
					updateStmt.setString(4, admin.getNumarTelefon());
					updateStmt.setString(5, admin.getEmail());
					updateStmt.setString(6, admin.getDataNasterii());
					updateStmt.setString(7, admin.getUsername());
					updateStmt.setString(8, admin.getParola());
					updateStmt.setInt(9, 0);
					updateStmt.setInt(10, admin instanceof Admin ? 1 : 0); // Check if user is an instance of Admin
					updateStmt.setString(11, admin.getUsername());
					updateStmt.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void importImprumuturi(Connection conn) {
		String sql = "SELECT * FROM imprumuturi, utilizatori, carti WHERE imprumuturi.user_id = utilizatori.user_id AND imprumuturi.carte_id = carti.carte_id";

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				for (Client i : Biblioteca.clienti) {
					if (i.getUsername() == rs.getString("Username") && i.getParola() == rs.getString("Parola")) {
						Carte book = new Carte(rs.getString("titlu"), rs.getString("autor"), rs.getString("editura"),
								rs.getString("categorie"), rs.getString("ISBN"), rs.getInt("nr_exemplare_disponibile"),
								rs.getInt("nr_exemplare_totale"), rs.getInt("anul_aparitiei"));
						boolean r = false;
						if (rs.getString("este_returnat").equals("1"))
							r = true;
						i.addCarteImprumutata(new CarteImprumutata(book, rs.getString("dataImprumutului"),
								rs.getString("dataReturnarii"), rs.getString("dataScadenta"), r));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exportImprumuturi(Connection conn) throws SQLException {
	    String tr_sql = "TRUNCATE TABLE imprumuturi";
	    try (Statement s1 = conn.createStatement()) {
	        s1.execute(tr_sql);
	    }

	    String userSql = "SELECT user_id FROM utilizatori WHERE username = ? AND parola = ?";
	    String bookSql = "SELECT carte_id FROM carti WHERE ISBN = ?";
	    String insertSql = "INSERT INTO imprumuturi(user_id, carte_id, data_scadenta, data_imprumutului, data_returnarii, este_returnat) VALUES (?,?,?,?,?,?)";

	    for (Client client : Biblioteca.clienti) {
	        try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
	            userStmt.setString(1, client.getUsername());
	            userStmt.setString(2, client.getParola());
	            ResultSet rsUser = userStmt.executeQuery();

	            if (rsUser.next()) {
	                int userId = rsUser.getInt("user_id");

	                for (CarteImprumutata carte : client.getCartiImprumutate()) {
	                    try (PreparedStatement bookStmt = conn.prepareStatement(bookSql)) {
	                        bookStmt.setString(1, carte.getISBN());
	                        ResultSet rsBook = bookStmt.executeQuery();

	                        if (rsBook.next()) {
	                            int bookId = rsBook.getInt("carte_id");

	                            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
	                                insertStmt.setInt(1, userId);
	                                insertStmt.setInt(2, bookId);
	                                insertStmt.setString(3, carte.getDataScadenta());
	                                insertStmt.setString(4, carte.getDataImprumutului());
	                                insertStmt.setString(5, carte.getDataReturnarii());
	                                insertStmt.setBoolean(6, carte.getEsteReturnat());
	                                insertStmt.executeUpdate();
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	}


	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/LibraryManagementSystem";
			String user = "root";
			String password = "root";

			conn = DriverManager.getConnection(url, user, password);

			Biblioteca.startBiblioteca();
			importUsers(conn);
			importBooks(conn);
			importImprumuturi(conn);
			for (Client i : Biblioteca.clienti) {
				System.out.println(i.getNume());
			}

			Statement stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
