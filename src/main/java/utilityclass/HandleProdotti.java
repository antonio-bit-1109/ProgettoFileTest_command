package utilityclass;


import database.DTOs.ProdottoDTO;

import java.util.*;

// contiene la lista dei prodotti, che viene pulita dopo che prelevo i dati dal db ed una lista di backup che contiene tutte le transazioni effettuate
// che serviranno per elaborare uno storico delle transazioni.
public class HandleProdotti {

    private List<ProdottoDTO> listaProdotti;
    private Map<String, String> mappaTransaz;


    public void setListaProdotti(List<ProdottoDTO> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public void setMappaTransaz(Map<String, String> mappaTransaz) {
        this.mappaTransaz = mappaTransaz;
    }

    //costrutt
    public HandleProdotti() {
        setListaProdotti(new ArrayList<>());
        setMappaTransaz(new HashMap<>());
    }

    public Map<String, String> getMappaTransaz() {
        return mappaTransaz;
    }

    public List<ProdottoDTO> getListaProdotti() {
        return listaProdotti;
    }

    public int getListaSize() {
        return this.listaProdotti.size();
    }

    public int getMapSize() {
        return this.getMappaTransaz().size();
    }

    public void AddToListProdotti(ProdottoDTO prod) {
        this.getListaProdotti().add(prod);
    }

    public void ClearMap() {
        getMappaTransaz().clear();
    }

    public void AddToMap(String value) {

        String uniqueKey = UUID.randomUUID().toString();
        this.getMappaTransaz().put(uniqueKey, value);
    }
}
