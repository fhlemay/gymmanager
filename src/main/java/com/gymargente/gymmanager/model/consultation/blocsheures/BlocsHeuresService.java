package com.gymargente.gymmanager.model.consultation.blocsheures;

import com.gymargente.gymmanager.model.client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class BlocsHeuresService {

    public static BlocsHeures getSumBlocsHeuresFor(Client client) {
        if (client == null) return new BlocsHeures(0, client, 0, 0, 0, 0);
        var sumBlocsHeuresOpt = new BlocsHeuresDao().getSumBlocsHeures(client);
        return sumBlocsHeuresOpt.orElseGet(
                () -> new BlocsHeures(0, client, 0, 0, 0, 0)
        );
    }

    public static List<BlocsHeures> getAllBlocsHeuresFor(Client client) {

        List<BlocsHeures> allBlocsHeures = new ArrayList<>();
        if (client == null) return allBlocsHeures;
        var allBlocHeuresOpt = new BlocsHeuresDao().getBlocHeuresfor(client);
        allBlocHeuresOpt.ifPresent(allBlocsHeures::addAll);
        return allBlocsHeures;
    }

    public static void deleteBlocsHeures(BlocsHeures blocsHeures) throws BlocsHeuresException {

        if (blocsHeures == null) throw new BlocsHeuresException("Aucun bloc d'heures n'est sélectionné.");
        if (blocsHeures.heuresRestantes() != blocsHeures.heuresAchetees())
            throw new BlocsHeuresException("On ne peut rembourser un bloc d'heures entamées.");
        if (blocsHeures.heuresReservees() != 0) throw new BlocsHeuresException("""
                On ne peut pas rembourser un bloc d'heures lorsqu'il y a une ou des consultations réservées.
                """);
        new BlocsHeuresDao().delete(blocsHeures);
    }


    public static void addBlocHeures(BlocsHeures bloc) {
        new BlocsHeuresDao().create(bloc);
    }
}
