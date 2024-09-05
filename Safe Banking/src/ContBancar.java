
public class ContBancar {
	
    private String iban;
    private String valuta;
    private double sold;
    private double limitaRetragere;
    private boolean blocat;

    public ContBancar(String iban, String valuta, double sold, double limitaRetragere) {
    	
        this.iban = iban;
        this.valuta = valuta;
        this.sold = sold;
        this.limitaRetragere = limitaRetragere;
        this.blocat = false;
        
    }

    public void blocheazaCont() {
        blocat = true;
    }

    public void deblocheazaCont() {
    	
        blocat = false;
        
    }

    public void alimenteazaCont(double suma, String valutaOperatie) throws ValutaGresitaException, ContBlocatException {
    	
        if (blocat) throw new ContBlocatException();
        if (!valuta.equals(valutaOperatie)) throw new ValutaGresitaException();
        sold += suma;
    }

    public void retrageBani(double suma, String valutaOperatie) throws ValutaGresitaException, FonduriInsuficienteException, DepasireLimitaException, ContBlocatException {
    	
        if (blocat) throw new ContBlocatException();
        if (!valuta.equals(valutaOperatie)) throw new ValutaGresitaException();
        if (suma > sold) throw new FonduriInsuficienteException();
        if (suma > limitaRetragere) throw new DepasireLimitaException();
        sold -= suma;
        
    }

    public double getSold() {
        return sold;
    }

    public static class ValutaGresitaException extends Exception {
        public ValutaGresitaException() {
            super("Valuta gresita.");
        }
    }

    public static class ContBlocatException extends Exception {
        public ContBlocatException() {
            super("Contul este blocat. ");
        }
    }

    public static class FonduriInsuficienteException extends Exception {
        public FonduriInsuficienteException() {
            super("Fonduri insuficiente.");
        }
    }

    public static class DepasireLimitaException extends Exception {
        public DepasireLimitaException() {
            super("Limita de retragere depasita.");
        }
    }
}
