package java.oop.gestore.eventi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Concerto extends Evento {
	
	private LocalTime oraConcerto;
	private float prezzoBiglietto;
	
	public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime oraConcerto, float prezzoBiglietto) {
		super(titolo, data, postiTotali);
		this.oraConcerto = oraConcerto;
		this.prezzoBiglietto = prezzoBiglietto;
	   }

	public LocalTime getOraConcerto() {
		return oraConcerto;
	}

	public void setOraConcerto(LocalTime oraConcerto) {
		this.oraConcerto = oraConcerto;
	}

	public float getPrezzoBiglietto() {
		return prezzoBiglietto;
	}

	public void setPrezzoBiglietto(float prezzoBiglietto) {
		this.prezzoBiglietto = prezzoBiglietto;
	   }

	public String formattaData () {
	   DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	   return this.getData().format(f);
       }
	
	public String formattaOra () {
	   DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm:ss");
	   return this.getOraConcerto().format(f);
       }
   
	public String formattaPrezzo() {  
		DecimalFormat prezzoFormattato = new DecimalFormat("##.##€");
		return prezzoFormattato.format(this.prezzoBiglietto);
	}

	@Override
	public String toString() {
		return super.toString() + " - Ora " + this.formattaOra() + " - Prezzo del biglietto: " + this.formattaPrezzo();

	}
}
