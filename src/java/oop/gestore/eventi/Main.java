package java.oop.gestore.eventi;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		ProgrammEventi programma = creaProgrammaEventi();
		boolean b = true;
		do {
			int operazione = elencoOperazioni();
			switch (operazione) {
				case 1:
					aggiungiEventoLista(programma);
					break;
				case 2:
					prenotaEvento(programma);
					break;
				case 3:
					disdiciEvento(programma);
					break;
				case 4:
					stampaListaOrdinata(programma);
					break;
				case 5:
					svuotaLista(programma);
					break;
				case 6:
					b = false;
					break;
				}
		} while (b);	
} 
	
	public static int elencoOperazioni () { 
		Scanner input = new Scanner(System.in);
		System.out.println("[1] Aggiungi un evento al programma");
		System.out.println("[2] Effettua una prenotazione");
		System.out.println("[3] Disdici una prenotazione");
		System.out.println("[4] Stampa la lista degli eventi ordinati per data");
		System.out.println("[5] Svuota la lista");
		System.out.println("[6] Termina");
		int operazione;
		do {
			System.out.println("Seleziona un'operazione: (inserire un numero da 1 a 6)");
			operazione = input.nextInt();
		} while (operazione < 1 || operazione > 6);
		return operazione;
	}	

	public static ProgrammEventi creaProgrammaEventi() {
		Scanner input = new Scanner(System.in);
		System.out.println("Inserire il titolo della lista da creare: ");
		String titoloLista = input.nextLine();
		ProgrammEventi progEventi = new ProgrammEventi(titoloLista);
		System.out.println("Inserire gli eventi della lista ");
		Evento e;
		String risposta;
		do {
			e = creaEvento();
			progEventi.aggiungiEvento(e); 
			System.out.println("digitare E se si vuole inserire un altro evento, digitare T se la sequenza di eventi è terminata");
			risposta = input.nextLine();
		} while (risposta.equals("E"));
		return progEventi;
	  }
	
	public static void aggiungiEventoLista(ProgrammEventi programma) {
		Evento e = creaEvento();
		programma.aggiungiEvento(e); 
	    }
	
	public static void prenotaEvento(ProgrammEventi programma) {
		Scanner input = new Scanner(System.in);
		System.out.println("Inserire il titolo dell'evento");
		String titoloEvento = input.nextLine();
		Evento e = programma.cercaEvento(titoloEvento);
		
		//Chiede all'utente quanti posti vuole prenotare
		//Nota: il metodo prenota() della classe Evento aggiunge uno ai posti prenotati
		System.out.println("Quanti posti vuoi prenotare per l'evento?");
		int postiDaPrenotare = input.nextInt();
		int postiPrenotatiUtente = 0;
		boolean esito;
		int i = 1;
		do {
			esito = e.prenota();
			if (!esito)
					postiPrenotatiUtente++; 
			i++;
			} while (!esito && i <= postiDaPrenotare);
			
			//Stampa i posti che l'utente è riuscito a prenotare
			System.out.println("Esito operazione: hai prenotato " + postiPrenotatiUtente + " posti");
			int postiDisponibili = e.getPostiTotali() - e.getPostiPrenotati();
			System.out.println("I posti disponibili sono: " + postiDisponibili);
			System.out.println("I posti prenotati in totale sono: " + e.getPostiPrenotati());
        }		

	
	public static void disdiciEvento(ProgrammEventi programma) {
		Scanner input = new Scanner(System.in);
		System.out.println("Inserire il titolo dell'evento");
		String titoloEvento = input.nextLine();
		Evento e = programma.cercaEvento(titoloEvento);
		
		//Chiede all'utente quanti posti vuole disdire
		//Nota: il metodo disdici() riduce di uno i posti prenotati
		System.out.println("Quanti posti vuoi disdire per l'evento?");
		int postiDaDisdire = input.nextInt();
		int postiDisdettiUtente = 0;
		boolean esito;
		int i = 1;
		do {
			esito = e.disdici();
			if (!esito)
				postiDisdettiUtente++; 
				i++;
			} while (!esito && i <= postiDaDisdire);
				
			//Stampa i posti che l'utente è riuscito a disdire
			System.out.println("Esito operazione: hai disdetto " + postiDisdettiUtente + " posti");
			int postiDisponibili = e.getPostiTotali() - e.getPostiPrenotati();
			System.out.println("I posti disponibili sono: " + postiDisponibili);
			int postiPrenotati = e.getPostiPrenotati();
			System.out.println("I posti prenotati in totale da tutti gli utenti sono: " + postiPrenotati);
	}
	
	public static void stampaListaOrdinata(ProgrammEventi programma) {
		String s = programma.ordinaListaEventi();
		System.out.println(s);				
	}
	
	public static void svuotaLista(ProgrammEventi programma) {
		programma.svuotaListaEventi();
	}
	
	public static Evento creaEvento() {
		Scanner input = new Scanner(System.in);
		System.out.println("Specificare l'evento da creare: (generico/concerto): ");
		String tipoEvento = input.nextLine();
		Evento e;
		if (tipoEvento.equals("generico")) 
			 e = creaEventoGenerico();
		else 
			 e = creaConcerto();
		return e;
	    }
	
	public static Evento creaEventoGenerico() {
		Scanner input = new Scanner(System.in);
		System.out.println("Inserire il titolo dell'evento");
		String titolo = input.nextLine();
		LocalDate dataEvento;
		LocalDate dataCorrente = LocalDate.now();
		boolean isEventoPassato;
		do{
			System.out.println("Inserire il giorno dell'evento: (numero da 1 a 31) ");
			int giorno = input.nextInt();
			System.out.println("Inserire il mese dell'evento: (numero da 1 a 12) ");
			int mese = input.nextInt();
			System.out.println("Inserire l'anno dell'evento: ");
			int anno = input.nextInt();
			dataEvento = LocalDate.of(anno, mese, giorno);
			isEventoPassato = dataEvento.isBefore(dataCorrente);
			if (isEventoPassato)	
				System.out.println("Data non valida, inserire una data successiva a quella attuale");
			} while (isEventoPassato);
			
		int postiTotali;
		do{
			System.out.println("Inserire il numero di posti totale: (numero maggiore di zero)");
			postiTotali= input.nextInt();
			} while (postiTotali <= 0);
		Evento evento = new Evento(titolo, dataEvento, postiTotali);
		System.out.println(evento);
		return evento;
	}
	
	public static Concerto creaConcerto() {
		Scanner input = new Scanner(System.in);
		System.out.println("Inserire il titolo dell'evento");
		String titolo = input.nextLine();
		LocalDate dataConcerto;
		LocalDate dataCorrente = LocalDate.now();
		boolean isConcertoPassato;
		do{
			System.out.println("Inserire il giorno dell'evento: (numero da 1 a 31) ");
			int giorno = input.nextInt();
			System.out.println("Inserire il mese dell'evento: (numero da 1 a 12) ");
			int mese = input.nextInt();
			System.out.println("Inserire l'anno dell'evento: ");
			int anno = input.nextInt();
			dataConcerto = LocalDate.of(anno, mese, giorno);
			isConcertoPassato = dataConcerto.isBefore(dataCorrente);
			if (isConcertoPassato)	
				System.out.println("Data non valida, inserire una data successiva a quella attuale");
			} while (isConcertoPassato);
			
		int postiTotali;
		do{
			System.out.println("Inserire il numero di posti totale: (numero maggiore di zero)");
			postiTotali= input.nextInt();
			} while (postiTotali <= 0);
		
		LocalTime oraConcerto;
		LocalTime oraCorrente = LocalTime.now();
		boolean isOraPassata;
		long differenza = dataConcerto.toEpochDay() - dataCorrente.toEpochDay();
		if (differenza == 0) 
			do {
				System.out.println("Inserire l'ora dell'evento: (numero da 00 a 24)");
				int ora = input.nextInt();
				System.out.println("Inserire i minuti dell'evento: (numero da 0 a 60)");
				int minuti = input.nextInt();
				System.out.println("Inserire i secondi dell'evento: (numero da 0 a 60)");
				int secondi = input.nextInt();
				oraConcerto = LocalTime.of(ora, minuti, secondi);
				isOraPassata = oraConcerto.isBefore(oraCorrente);
				if (isOraPassata)	
					System.out.println("Ora non valida, inserire un'orario successivo a quello attuale");
				} while (isOraPassata);
		else {
				System.out.println("Inserire l'ora dell'evento: (numero da 00 a 24)");
				int ora = input.nextInt();
				System.out.println("Inserire i minuti dell'evento: (numero da 0 a 60)");
				int minuti = input.nextInt();
				System.out.println("Inserire i secondi dell'evento: (numero da 0 a 60)");
				int secondi = input.nextInt();
				oraConcerto = LocalTime.of(ora, minuti, secondi);
				}
			
		System.out.println("Inserire il prezzo del biglietto ");
		float prezzoBiglietto= input.nextFloat();
		Concerto concerto = new Concerto(titolo, dataConcerto, postiTotali, oraConcerto, prezzoBiglietto);
		System.out.println(concerto);
		return concerto;
	}
	
	
}  
