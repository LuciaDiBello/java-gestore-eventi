package java.oop.gestore.eventi;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ProgrammEventi {
	private String titoloLista;
	List<Evento> listaEventi;
	
	public ProgrammEventi(String titoloLista) {
		this.titoloLista = titoloLista;
		this.listaEventi = new ArrayList<>();
	    }
	
	public void aggiungiEvento(Evento evento) {
		listaEventi.add(evento);
	    }
	
	public List<Evento> eventiInDataPrefissata(LocalDate data) {
		List<Evento> listaEventiInDataPrefissata = new ArrayList<Evento>();
		for (int i = 0; i < listaEventi.size(); i++) {
			if (data.equals(listaEventi.get(i).getData())) 
				listaEventiInDataPrefissata.add(listaEventi.get(i));
		     }	
		return listaEventiInDataPrefissata;
	    }	
	
	public int contaEventiLista() {
		return this.listaEventi.size();
	}
	
	public void svuotaListaEventi() {
		listaEventi.clear();
	}
	
	public String ordinaListaEventi() {
		  Collections.sort(listaEventi);
		  String s = "Lista degli eventi ordinati per data \n TitoloLista: " + this.titoloLista + "\n";
		  for (int i = 0; i < listaEventi.size(); i++) {
		       s = s + listaEventi.get(i).toString() + "\n";
		  }
		  return s;
	}
	
	public Evento cercaEvento(String titoloEvento) {
		Evento e = null;
		for (int i = 0; i < listaEventi.size(); i++) 
			if (titoloEvento.equals(listaEventi.get(i).getTitolo())) { 
					e = listaEventi.get(i);
					break;
			 }
		return e;
       }
}	




