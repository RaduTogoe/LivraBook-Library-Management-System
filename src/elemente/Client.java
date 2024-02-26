package elemente;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
public class Client extends Utilizator{
	private int taxaPlata;
	private List<CarteImprumutata> cartiImprumutate;
	
	public Client() {
		super();
		taxaPlata = 0;
	}
	
	public Client(String nume, String prenume, String dataNasterii, String email, String numarTelefon, String adresa, String username, String parola) {
		super(nume, prenume, dataNasterii, email, numarTelefon, adresa, username, parola);
		cartiImprumutate = new ArrayList<>();
		taxaPlata = 0;
	}
	
	public Client(String username, String parola, String nume, String prenume, String dataNasterii, String email, String numarTelefon, String adresa, int taxa) {
		super(nume, prenume, dataNasterii, email, numarTelefon, adresa, username, parola);
		cartiImprumutate = new ArrayList<>();
		this.taxaPlata = taxa;
	}
	
	public Client(Client client) {
		super(client.getNume(), client.getPrenume(), client.getDataNasterii(), client.getEmail(), client.getNumarTelefon(), client.getAdresa(), client.getUsername(), client.getParola());
		this.taxaPlata = client.taxaPlata;
		cartiImprumutate = new ArrayList<>();
	}
	
	public void imprumutaCarte(Carte carte) {
		cartiImprumutate.add(new CarteImprumutata(carte, calculeazaDataCurenta()));
	}
	
	public void addCarteImprumutata(CarteImprumutata carte) {
		cartiImprumutate.add(carte);
	}
	public static String calculeazaDataCurenta() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		 LocalDateTime now = LocalDateTime.now();  
		 //System.out.println(dtf.format(now));
		 return (dtf.format(now));
	}
	
	public List<CarteImprumutata> getCartiImprumutate() {
		return cartiImprumutate;
	}
	
	public void setCartiImprumutate(List<CarteImprumutata> carti) {
		cartiImprumutate = carti;
	}
	public int getTaxaPlata() {
		return taxaPlata;
	}
	public void setTaxaPlata(int taxa) {
		taxaPlata = taxa;
	}
}