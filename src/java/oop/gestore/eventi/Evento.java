package java.oop.gestore.eventi;

import java.time.LocalDate;
import java.util.Collections;
import java.time.format.DateTimeFormatter;

public class Evento implements Comparable <Evento>{
	private String titolo;
	private LocalDate data;
	private int	postiTotali;
	private int	postiPrenotati;
	
    	public Evento (String titolo, LocalDate data, int postiTotali) {
    		this.titolo = titolo;
    		this.data = data;
    		this.postiTotali = postiTotali;
    		this.postiPrenotati = 0;
    	}
	
    	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getPostiTotali() {
		return postiTotali;
	}

	public int getPostiPrenotati() {
		return postiPrenotati;
	}    
	
	//Prenota: aggiunge uno ai posti prenotati. 
	//Se non ci sono posti disponibili restituisce un messaggio di avviso.
	public boolean prenota() {
		int postiDisponibili = this.postiTotali - this.postiPrenotati;
		LocalDate dataCorrente = LocalDate.now();
		boolean errore = false;
		if (postiDisponibili == 0) {
			 System.out.println("Non è possibile prenotare, non ci sono posti disponibili");
			 errore = true;
		       }
		else this.postiPrenotati++;
		return errore;
	}
	
	//Disdici: riduce di uno i posti prenotati. 
	//Se non ci sono prenotazioni restituisce un messaggio di avviso.
	public boolean disdici() {
		LocalDate dataCorrente = LocalDate.now();
		boolean errore = false;
		if (postiPrenotati == 0) {
			System.out.println("Non è possibile disdire, non ci sono posti prenotati");
			errore = true;
		   }	
		else this.postiPrenotati--;
		return errore;
	}
	
	//Override del metodo toString()
	//Viene restituita una stringa contenente la data formattata e il titolo dell'evento
	@Override
	public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "Data evento: " + this.data.format(f) + " - Titolo: " + this.titolo;
	}
		
	@Override
	public int compareTo (Evento e) {
		if (this.data.isAfter(e.getData()))
			 return +1;
		else 
			 return -1;
	}
	
}



