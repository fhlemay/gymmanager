package com.gymargente.gymmanager.model.consultation.blocsheures;

import com.gymargente.gymmanager.model.client.Client;

public record BlocsHeures(int id,
                          Client client,
                          int heuresAchetees,
                          int heuresReservees,
                          int heuresRestantes,
                          int idPrix) {
    public BlocsHeures(Client client,
                int heuresAchetees,
                int heuresReservees,
                int heuresRestantes,
                int idPrix) {
        this(0,
                client,
                heuresAchetees,
                heuresReservees,
                heuresRestantes,
                idPrix
        );
    }
}
