package command.implem;

import command.interf.command;
import utilityclass.HandleProdotti;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Map;

public class StoricoTransazioniCommand implements command {

    private HandleProdotti handleProdotti;

    public void setHandleProdotti(HandleProdotti handleProdotti) {
        this.handleProdotti = handleProdotti;
    }

    //costrutt riceve oggetto handleProdotti da fuori, da shell
    public StoricoTransazioniCommand(HandleProdotti handleprod) {
        setHandleProdotti(handleprod);
    }

    public HandleProdotti getHandleProdotti() {
        return handleProdotti;
    }

    // qui dentro finira la mappa presente in handleFile che a seconda dell intero identificativo mi specifica che tipo di azione è stata fatta
    // 1 - get di tutti i prodotti dal db
    // 2- modifica nome di un prodotto sul db
    // 3- estrapolazione singolo prodotto da db
    // 4- scrittura su file dei dati estrapolati da db

    @Override
    public void Execute() {

        System.out.println();
        System.out.println("STORICO TRANSAZIONI APPLICAZIONE:");
        System.out.println("----------------------------------");
        CicleMap();
        System.out.println("----------------------------------");
    }


    private void CicleMap() {

        LocalDateTime currentDate = LocalDateTime.now();

        if (getHandleProdotti().getMappaTransaz().isEmpty()) {
            System.out.println("nessuna transazione ancora effettuata.");

        } else {

            Map<Integer, String> mymap = getHandleProdotti().getMappaTransaz();

            for (Map.Entry<Integer, String> entry : mymap.entrySet()) {
                // System.out.println("ID: " + entry.getKey() + ", Descrizione: " + entry.getValue());
                System.out.println("- " + entry.getValue() + " In Data --> " + currentDate);

            }

        }

    }
}
