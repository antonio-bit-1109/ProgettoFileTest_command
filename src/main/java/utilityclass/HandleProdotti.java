package utilityclass;


import database.DTOs.ProdottoDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// contiene la lista dei prodotti, che viene pulita dopo che prelevo i dati dal db ed una lista di backup che contiene tutte le transazioni effettuate
// che serviranno per elaborare uno storico delle transazioni.
public class HandleProdotti {

    // mappa nel quale inserire una stringa che identifichi l'operazione fatta (key) ed il value rappresenta una lista di oggetti ProdottoDto
    // nel quale ce ne possono essere uno o piu oggetti ProdottoDto
    // private Map<String, List<ProdottoDTO>> mapProdotti;
//     private List<ProdottoDTO> listaProdotti;
//
//
//    public void setMapProdotti(Map<String, List<ProdottoDTO>> mapProdotti) {
//        this.mapProdotti = mapProdotti;
//    }
//
//    //costrutt
//    public HandleProdotti() {
//        setMapProdotti(new HashMap<>());
//    }
//
//    public Map<String, List<ProdottoDTO>> getMapProdotti() {
//        return mapProdotti;
//    }

//    public void AddToMap(String key, ProdottoDTO prod) {
//        ArrayList<ProdottoDTO> list = new ArrayList<>();
//        getMapProdotti().put(key, list.add(prod));
//    }
}
