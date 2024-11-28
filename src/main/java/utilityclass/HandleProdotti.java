package utilityclass;


import database.DTOs.ProdottoDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// contiene la lista dei prodotti, che viene pulita dopo che prelevo i dati dal db ed una lista di backup che contiene tutte le transazioni effettuate
// che serviranno per elaborare uno storico delle transazioni.
public class HandleProdotti {

    private List<ProdottoDTO> listaProdotti;
    private Map<Integer, String> mappaTransaz;

    public void setListaProdotti(List<ProdottoDTO> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public void setMappaTransaz(Map<Integer, String> mappaTransaz) {
        this.mappaTransaz = mappaTransaz;
    }

    //costrutt
    public HandleProdotti() {
        setListaProdotti(new ArrayList<>());
        setMappaTransaz(new HashMap<>());
    }

    public Map<Integer, String> getMappaTransaz() {
        return mappaTransaz;
    }

    public List<ProdottoDTO> getListaProdotti() {
        return listaProdotti;
    }

    public int getListaSize() {
        return this.listaProdotti.size();
    }

    public void AddToListProdotti(ProdottoDTO prod) {
        this.getListaProdotti().add(prod);
    }
}
