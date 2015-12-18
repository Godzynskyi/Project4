package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Client;

/**
 * Created by Java Developer on 27.11.2015.
 */
public interface ClientDAO {

    /**
     * Returns Client by ID
     * @param id ID of Client
     * @return Client from DB
     */
    Client getClient(int id);

    /**
     * Add Client to DB
     *
     * @return ID if successfully added or -1 otherwise
     *
     */
    int addClient(Client client);
}
