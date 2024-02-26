package elemente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CarteImprumutata extends Carte {

	private String dataImprumutului;
	private String dataScadenta;
	private String dataReturnarii;
	private boolean esteReturnat;

	public CarteImprumutata(String titlu, String autor, String editura, String categorie, String ISBN,
			int nrExemplareDisponibile, int nrExemplareTotale, int anulAparitiei) {
		super(titlu, autor, editura, categorie, ISBN, nrExemplareDisponibile, nrExemplareTotale, anulAparitiei);
		this.esteReturnat = false;
	}

	public CarteImprumutata(Carte carte, String dataImprumut) {
		super(carte.getTitlu(), carte.getAutor(), carte.getEditura(), carte.getCategorie(), carte.getISBN(),
				carte.getNrExemplareDisponibile(), carte.getNrExemplareTotale(), carte.getAnulAparitiei());
		this.dataImprumutului = dataImprumut;
		this.dataScadenta = calculeazaDataScadenta(dataImprumut);
		this.esteReturnat = false;
	}

	public CarteImprumutata(Carte carte, String a, String b, String c, boolean esteReturnat) {
		super(carte.getTitlu(), carte.getAutor(), carte.getEditura(), carte.getCategorie(), carte.getISBN(),
				carte.getNrExemplareDisponibile(), carte.getNrExemplareTotale(), carte.getAnulAparitiei());
		dataImprumutului = a;
		dataReturnarii = b;
		dataScadenta = c;
		this.esteReturnat = esteReturnat;
	}
	public void returneazaCarte() {
		dataReturnarii = Client.calculeazaDataCurenta();
	}

	public String getDataReturnarii() {
		return dataReturnarii;
	}

	public String getDataImprumutului() {
		return dataImprumutului;
	}

	public String getDataScadenta() {
		return dataScadenta;
	}

	public void setEsteReturnat(boolean val) {
		this.esteReturnat = val;
	}

	public Boolean getEsteReturnat() {
		return esteReturnat;
	}

	public static String calculeazaDataScadenta(String inputDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(inputDate, formatter);
		LocalDate newDate = date.plusWeeks(2);
		String result = newDate.format(formatter);
		return result;
	}

	public static int calculeazaTaxa(String inputDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(inputDate, formatter);
		int weeksPassed = (int)ChronoUnit.WEEKS.between(date, LocalDate.now());

		return (weeksPassed * (weeksPassed + 1) * 10) / 2;
	}
}
